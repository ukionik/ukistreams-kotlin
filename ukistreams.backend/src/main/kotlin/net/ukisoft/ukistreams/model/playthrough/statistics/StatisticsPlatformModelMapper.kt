package net.ukisoft.ukistreams.model.playthrough.statistics

import net.ukisoft.ukistreams.entities.Game
import net.ukisoft.ukistreams.entities.Platform
import net.ukisoft.ukistreams.entities.Playthrough
import java.time.Duration
import java.util.function.Consumer

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 01:24
 */
class StatisticsPlatformModelMapper {
    fun toModel(platforms: List<Platform>, platformMap: Map<Long, List<Pair<Game, Playthrough>>>)
            : StatisticsPlatformModel {
        val list = ArrayList<StatisticsPlatformItemModel>()
        platforms.forEach(Consumer { platform: Platform ->
            val platformGames: List<Pair<Game, Playthrough>> = platformMap[platform.id] ?: ArrayList()
            val model = createPlatformItemModel(platform.shortName!!, platformGames)
            list.add(model)
        })
        val allValues: List<Pair<Game, Playthrough>> = platformMap.values.flatten()
        val total = createPlatformItemModel("", allValues)
        return StatisticsPlatformModel(list, total)
    }

    private fun createPlatformItemModel(platformName: String, games: List<Pair<Game, Playthrough>>)
            : StatisticsPlatformItemModel {
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

        return StatisticsPlatformItemModel(
            platformName,
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