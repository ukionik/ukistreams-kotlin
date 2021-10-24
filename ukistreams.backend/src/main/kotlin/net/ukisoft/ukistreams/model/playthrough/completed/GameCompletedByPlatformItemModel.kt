package net.ukisoft.ukistreams.model.playthrough.completed

import java.time.Duration
import java.time.LocalDate

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 19:30
 */
data class GameCompletedByPlatformItemModel(
    val gameName: String,
    val genreId: Long,
    val genreName: String,
    val duration: Duration,
    val endDate: LocalDate,
    val rate: Double?,
    val difficulty: Double?,
    val pickedBy: String,
    val vodParts: List<GameCompletedVodPartItemModel>
)