package net.ukisoft.ukistreams.model.playthrough.statistics

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 01:05
 */
data class StatisticsGenreModel(
    val genres: List<StatisticsGenreItemModel>,
    val total: StatisticsGenreItemModel
)