package net.ukisoft.ukistreams.model.core

import net.ukisoft.ukistreams.entities.BaseEntity
import java.lang.reflect.ParameterizedType
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Query
import javax.persistence.criteria.*

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 02.12.2020 18:01
 */
abstract class CustomRepositoryImpl<TEntity : BaseEntity, TFilter : RepositoryFilter>
protected constructor() : CustomRepository<TEntity, TFilter> {
    private val persistentClass: Class<TEntity> = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<TEntity>

    @PersistenceContext
    private val entityManager: EntityManager? = null
    override fun findByFilter(filter: TFilter): List<TEntity> {
        val predicates: List<Predicate> = ArrayList()
        val builder: CriteriaBuilder = entityManager!!.criteriaBuilder
        val criteriaList: CriteriaQuery<TEntity> = builder.createQuery(persistentClass)
        val root: Root<TEntity> = criteriaList.from(persistentClass)
        criteriaList.select(root)
        createFilters(filter, builder, predicates, root)
        criteriaList.where(*predicates.toTypedArray())
        fetchFields(filter.fetchFields, root)
        val query: Query = entityManager.createQuery(criteriaList)
        return query.resultList as List<TEntity>
    }

    private fun fetchFields(fetchFields: List<FetchField>, root: Root<TEntity>) {
        for (fetchField in fetchFields) {
            createFetchByColumn(fetchField.name, fetchField.joinType, root)
        }
    }

    private fun createFetchByColumn(columnName: String, joinType: JoinType, root: Root<TEntity>): Fetch<*, *>? {
        //Разбиваем по символу '.' на колонки. Например project.id
        val columnNames = columnName.split(".").toTypedArray()
        var fetch: Fetch<*, *>? = null
        for (c in columnNames) {
            if (fetch == null) {
                fetch = root.fetch<Any, Any>(c, joinType)
            } else {
                fetch = fetch.fetch<String, JoinType>(c, joinType)
            }
        }
        return fetch
    }

    protected fun createFilters(
        filter: TFilter,
        builder: CriteriaBuilder?,
        predicates: List<Predicate>?,
        root: Root<TEntity>?
    ) {
    }

}