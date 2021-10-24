package net.ukisoft.ukistreams.model.platform

import net.ukisoft.ukistreams.entities.Platform
import net.ukisoft.ukistreams.model.core.EditModelMapper

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 05:34
 */
class PlatformEditModelMapper : EditModelMapper<Platform, PlatformEditModel> {
    override fun toModel(entity: Platform): PlatformEditModel {
        return PlatformEditModel(entity.id!!, entity.ordinal!!, entity.name!!, entity.shortName!!)
    }

    override fun toNewEntity(model: PlatformEditModel): Platform {
        val entity = Platform()
        updateEntity(entity, model)
        return entity
    }

    override fun updateEntity(entity: Platform, model: PlatformEditModel) {
        entity.ordinal = model.ordinal
        entity.name = model.name
        entity.shortName = model.shortName
    }
}