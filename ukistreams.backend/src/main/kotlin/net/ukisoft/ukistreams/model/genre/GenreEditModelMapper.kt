package net.ukisoft.ukistreams.model.genre

import net.ukisoft.ukistreams.entities.Genre
import net.ukisoft.ukistreams.model.core.EditModelMapper

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 07:21
 */
class GenreEditModelMapper : EditModelMapper<Genre, GenreEditModel> {
    override fun toModel(entity: Genre): GenreEditModel = GenreEditModel(entity.id!!, entity.ordinal!!, entity.name!!)

    override fun toNewEntity(model: GenreEditModel): Genre {
        val genre = Genre()
        updateEntity(genre, model)
        return genre
    }

    override fun updateEntity(entity: Genre, model: GenreEditModel) {
        entity.ordinal = model.ordinal
        entity.name = model.name
    }
}