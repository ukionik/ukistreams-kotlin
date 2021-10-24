package net.ukisoft.ukistreams.model.core

import net.ukisoft.ukistreams.entities.BaseEntity
import net.ukisoft.ukistreams.model.core.BaseEditModel

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 05:30
 */
interface EditModelMapper<TEntity : BaseEntity, TEditModel : BaseEditModel> {
    fun toModel(entity: TEntity): TEditModel
    fun toNewEntity(model: TEditModel): TEntity
    fun updateEntity(entity: TEntity, model: TEditModel)
}