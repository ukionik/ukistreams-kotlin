package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.entities.VodPart
import net.ukisoft.ukistreams.model.core.EditModelMapper

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 22.09.2020 01:20
 */
class VodPartEditModelMapper : EditModelMapper<VodPart, VodPartEditModel> {
    override fun toModel(entity: VodPart): VodPartEditModel = VodPartEditModel(
        entity.id!!,
        entity.ordinal!!,
        entity.url!!
    )

    override fun toNewEntity(model: VodPartEditModel): VodPart {
        val entity = VodPart()
        updateEntity(entity, model)
        return entity
    }

    override fun updateEntity(entity: VodPart, model: VodPartEditModel) {
        entity.ordinal = model.ordinal
        entity.url = model.url
    }
}