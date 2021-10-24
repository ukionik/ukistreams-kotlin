package net.ukisoft.ukistreams.model.genre

import net.ukisoft.ukistreams.core.ItemModelMapper

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 07:21
 */
class GenreItemModelMapper : ItemModelMapper<Genre?, GenreItemModel?> {
    fun toModel(entity: Genre): GenreItemModel {
        val model = GenreItemModel()
        model.id = entity.getId()
        model.ordinal = entity.getOrdinal()
        model.name = entity.getName()
        return model
    }
}