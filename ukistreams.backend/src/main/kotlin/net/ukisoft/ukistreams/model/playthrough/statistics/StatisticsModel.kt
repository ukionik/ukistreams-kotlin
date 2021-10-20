package net.ukisoft.ukistreams.model.playthrough.statistics

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 00:38
 */
data class StatisticsModel(
    val platformStatistics: StatisticsPlatformModel,
    val genreStatistics: StatisticsGenreModel
)