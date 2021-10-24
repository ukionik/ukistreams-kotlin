package net.ukisoft.ukistreams.model.platform

import net.ukisoft.ukistreams.entities.Platform
import net.ukisoft.ukistreams.model.core.ItemModelMapper

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 06:04
 */
class PlatformItemModelMapper : ItemModelMapper<Platform, PlatformItemModel> {
    override fun toModel(entity: Platform): PlatformItemModel {
        return PlatformItemModel(entity.id!!, entity.ordinal!!, entity.name!!, entity.shortName!!)
    }
}