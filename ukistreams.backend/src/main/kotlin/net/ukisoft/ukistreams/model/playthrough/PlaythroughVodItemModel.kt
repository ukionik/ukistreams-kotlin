package net.ukisoft.ukistreams.model.playthrough

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 03.10.2020 00:58
 */
data class PlaythroughVodItemModel(
    val id: Long,
    val type: String,
    val parts: List<PlaythroughVodPartItemModel>
)