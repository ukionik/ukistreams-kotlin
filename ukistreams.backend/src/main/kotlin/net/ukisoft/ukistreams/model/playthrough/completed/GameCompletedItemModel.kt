package net.ukisoft.ukistreams.model.playthrough.completed

import java.time.Duration
import java.time.LocalDate

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 25.10.2020 02:55
 */
data class GameCompletedItemModel (
    val gameName: String,
    val platformId: Long,
    val platformName: String,
    val genreId: Long,
    val genreName: String,
    val projectId: Long,
    val projectName: String,
    val duration: Duration,
    val endDate: LocalDate,
    val rate: Double?,
    val difficulty: Double?,
    val pickedBy: String,
    val vodParts: List<GameCompletedVodPartItemModel>,
    var index: Int = 0
)