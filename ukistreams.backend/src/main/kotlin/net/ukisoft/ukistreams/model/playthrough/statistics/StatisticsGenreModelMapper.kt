package net.ukisoft.ukistreams.model.playthrough.statistics

import net.ukisoft.ukistreams.entities.Game
import net.ukisoft.ukistreams.entities.Genre
import net.ukisoft.ukistreams.entities.Playthrough
import java.time.Duration

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 01:24
 */
class StatisticsGenreModelMapper {
    fun toModel(genres: List<Genre>, genreMap: Map<Long, List<Pair<Game, Playthrough>>>)
            : StatisticsGenreModel {
        val list = ArrayList<StatisticsGenreItemModel>()
        genres.forEach { genre ->
            val genreGames: List<Pair<Game, Playthrough>> = genreMap[genre.id] ?: ArrayList()
            list.add(createGenreItemModel(genre.name!!, genreGames))
        }
        val allValues: List<Pair<Game, Playthrough>> = genreMap.values.flatten()

        val total = createGenreItemModel("", allValues)
        return StatisticsGenreModel(list, total)
    }

    private fun createGenreItemModel(genreName: String, games: List<Pair<Game, Playthrough>>): StatisticsGenreItemModel {
        val totalMillis = games
            .mapNotNull { (_, value) -> value.duration?.toMillis() }
            .sum()

        val shortestGame = games.filter { (_, value) ->
            value.duration != null
        }.minByOrNull { x -> x.second.duration!! }

        val longestGame = games.filter { (_, value) ->
            value.duration != null
        }.maxByOrNull { x -> x.second.duration!! }

        val averageMillis = games
            .mapNotNull { (_, value) -> value.duration?.toMillis() }
            .average()

        val averageGameTime = Duration.ofMillis(averageMillis.toLong())

        val hours = Duration.ofMillis(totalMillis)

        return StatisticsGenreItemModel(
            genreName,
            games.size,
            hours,
            shortestGame?.second?.game?.name,
            shortestGame?.second?.duration,
            longestGame?.second?.game?.name,
            longestGame?.second?.duration,
            averageGameTime
        )
    }
}