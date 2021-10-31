package net.ukisoft.ukistreams.model.genre

import net.ukisoft.ukistreams.entities.Genre
import net.ukisoft.ukistreams.model.core.ItemModelMapper

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 07:21
 */
class GenreItemModelMapper : ItemModelMapper<Genre, GenreItemModel> {
    override fun toModel(entity: Genre): GenreItemModel = GenreItemModel(
        entity.id!!,
        entity.ordinal!!,
        entity.name!!
    )
}