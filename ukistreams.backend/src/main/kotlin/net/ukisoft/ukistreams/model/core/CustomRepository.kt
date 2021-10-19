package net.ukisoft.ukistreams.model.core

import net.ukisoft.ukistreams.entities.BaseEntity

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 02.12.2020 20:13
 */
interface CustomRepository<TEntity : BaseEntity, TFilter : RepositoryFilter> {
    fun findByFilter(filter: TFilter): List<TEntity>
}