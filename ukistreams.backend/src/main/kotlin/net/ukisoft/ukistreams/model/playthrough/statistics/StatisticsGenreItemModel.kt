package net.ukisoft.ukistreams.model.playthrough.statistics

import java.time.Duration

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 00:38
 */
data class StatisticsGenreItemModel(
    val genreName: String,
    val completedCount: Int,
    val hours: Duration,
    val shortestGameName: String?,
    val shortestGameTime: Duration?,
    val longestGameName: String?,
    val longestGameTime: Duration?,
    val averageGameTime: Duration?
)