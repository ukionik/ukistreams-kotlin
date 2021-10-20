package net.ukisoft.ukistreams.model.playthrough.completed

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 19:29
 */
data class GameCompletedPlatformModel(
    val platformName: String,
    val games: List<GameCompletedByPlatformItemModel>
)