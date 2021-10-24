package net.ukisoft.ukistreams.model.core

import net.ukisoft.ukistreams.entities.BaseEntity
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 05:58
 */
abstract class CrudAllServiceImpl<TEntity : BaseEntity, TEditModel : BaseEditModel, TItemModel : BaseItemModel> protected constructor(
    repository: JpaRepository<TEntity, Long>,
    modelMapper: EditModelMapper<TEntity, TEditModel>,
    itemModelMapper: ItemModelMapper<TEntity, TItemModel>
) : CrudServiceImpl<TEntity, TEditModel>(repository, modelMapper), FindAllService<TItemModel> {
    private val itemModelMapper: ItemModelMapper<TEntity, TItemModel>
    protected open fun defaultSort(): Sort? {
        return null
    }

    override fun findAll(): List<TItemModel> {
        val sort = defaultSort()
        val items = if (sort == null) {
            repository.findAll()
        } else {
            repository.findAll(sort)
        }
        return items.map(itemModelMapper::toModel)
    }

    init {
        this.itemModelMapper = itemModelMapper
    }
}