package net.ukisoft.ukistreams.model.genre

import net.ukisoft.ukistreams.model.core.BaseItemModel

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 07:21
 */
data class GenreItemModel(
    val id: Long,
    val ordinal: Int,
    val name: String
) : BaseItemModel()