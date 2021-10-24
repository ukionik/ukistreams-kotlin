package net.ukisoft.ukistreams.model.core

import net.ukisoft.ukistreams.entities.BaseEntity

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 06:03
 */
interface ItemModelMapper<TEntity : BaseEntity, TItemModel : BaseItemModel> {
    fun toModel(entity: TEntity): TItemModel
}