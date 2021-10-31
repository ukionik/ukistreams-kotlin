package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.model.core.BaseItemModel
import java.time.Duration
import java.time.LocalDate

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:24
 */
data class PlaythroughItemModel(
    val index: Int,
    val id: Long,
    val gameName: String,
    val platformName: String,
    val genreName: String,
    val rate: Double,
    val difficulty: Double,
    val region: String,
    val duration: Duration,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val pickedBy: String,
    val projectName: String,
    val firstPlaythrough: String,
    val blind: String,
    val vods: List<PlaythroughVodItemModel>,
    val comment: String
) : BaseItemModel()