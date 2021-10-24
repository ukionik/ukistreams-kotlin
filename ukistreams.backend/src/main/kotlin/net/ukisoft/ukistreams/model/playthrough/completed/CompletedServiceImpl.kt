package net.ukisoft.ukistreams.model.playthrough.completed

import net.ukisoft.ukistreams.entities.Platform
import net.ukisoft.ukistreams.entities.Playthrough
import net.ukisoft.ukistreams.entity.Game
import net.ukisoft.ukistreams.model.core.FetchField
import net.ukisoft.ukistreams.model.core.RepositoryFilter
import net.ukisoft.ukistreams.model.platform.PlatformRepository
import net.ukisoft.ukistreams.model.playthrough.PlaythroughRepository
import net.ukisoft.ukistreams.model.playthrough.VodPartRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Consumer
import java.util.function.Function

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 25.10.2020 02:53
 */
@Service
@Transactional
class CompletedServiceImpl @Autowired constructor(
    playthroughRepository: PlaythroughRepository,
    vodPartRepository: VodPartRepository,
    platformRepository: PlatformRepository
) : CompletedService {
    private val playthroughRepository: PlaythroughRepository
    private val vodPartRepository: VodPartRepository
    private val platformRepository: PlatformRepository
    private val gameCompletedItemModelMapper: GameCompletedItemModelMapper
    override fun findAll(): List<GameCompletedItemModel> {
        val vodPartRepositoryFilter = RepositoryFilter()
        vodPartRepositoryFilter.fetchFields = listOf(FetchField.left("vod"))
        val vodGroup = vodPartRepository.findByFilter(vodPartRepositoryFilter)
            .groupBy { it.vod!! }
        val filter = RepositoryFilter()
        filter.fetchFields = listOf(
            FetchField.left("game"),
            FetchField.left("game.platform"),
            FetchField.left("game.genre"),
            FetchField.left("game.review"),
            FetchField.left("project")
        )
        val playthroughs = playthroughRepository.findByFilter(filter)
        val playthroughVodGroup = vodGroup.entries
            .groupBy { it.key!!.playthrough!!.id }

        val gameMap = playthroughs
            .groupBy { it.game!! }
            .map { x -> x.key to x.value.maxByOrNull { it.endDate!! } }
            .filter { (_, value) -> value != null }
            .map { (key, value) -> key to value!! }

        val items = gameMap
            .sortedByDescending { (_, value) -> value.endDate }
            .sortedByDescending { (_, value) -> value.id }
            .map { (key, value) ->
                gameCompletedItemModelMapper.toModel(key, value,
                    playthroughVodGroup.getOrDefault(value.id, ArrayList()).map { it.toPair() })
            }

        for (i in items.indices) {
            val item = items[i]
            item.index = items.size - i
        }
        return items
    }

    override fun findByPlatform(): List<GameCompletedPlatformModel> {
        val vodPartRepositoryFilter = RepositoryFilter()
        vodPartRepositoryFilter.fetchFields = listOf(FetchField.left("vod"))
        val vodGroup = vodPartRepository.findByFilter(vodPartRepositoryFilter)
        val filter = RepositoryFilter()
        filter.fetchFields = listOf(FetchField.left("game"), FetchField.left("game.genre"), FetchField.left("game.review"))
        val playthroughs = playthroughRepository.findByFilter(filter)
        val platforms = platformRepository.findAll(Sort.by(Sort.Direction.ASC, "ordinal"))
        val playthroughVodGroup = vodGroup
            .groupBy { it -> it. }
            .collect(Collectors.groupingBy(Function<T, K> { x: T -> x.getKey().getPlaythrough().getId() }))
        val gameMap = playthroughs.parallelStream()
            .collect(
                Collectors.toMap<Any, Any, Any>(
                    Playthrough::getGame,
                    Function<Any, Any?> { x: Playthrough? -> x },
                    BinaryOperator<Any> { x1: Any, x2: Any ->
                        if (x1.getEndDate().isAfter(x2.getEndDate())) x1 else x2
                    })
            )
        val gameByPlatformGroup = gameMap.entries.parallelStream()
            .filter { (key): Map.Entry<Any, Any> -> key.getPlatform() != null }
            .collect<Map<Any?, List<Map.Entry<Any, Any>>>, Any>(
                Collectors.groupingBy<Any, Any>(
                    Function<Any, Any> { (key): Map.Entry<Any, Any?> -> key.getPlatform().getId() })
            )
        val gamePlatforms = ArrayList<GameCompletedPlatformModel>()
        val mapper = GameCompletedByPlatformItemModelMapper()
        platforms.forEach(Consumer { platform: Platform ->
            val list = gameByPlatformGroup[platform.id] ?: ArrayList()
            val games = list.stream()
                .sorted(Comparator.comparing(Function<Map.Entry<Any, Any>, Any> { (key): Map.Entry<Any, Any> -> key.getName() }))
                .map { (key, value): Map.Entry<Any, Any> ->
                    mapper.toModel(
                        key, value, playthroughVodGroup.getOrDefault(value.getId(), ArrayList<E>())
                    )
                }
                .collect<List<GameCompletedByPlatformItemModel>, Any>(Collectors.toList<GameCompletedByPlatformItemModel>())
            val model = GameCompletedPlatformModel(platform, games)
            gamePlatforms.add(model)
        })
        return gamePlatforms
    }

    init {
        this.playthroughRepository = playthroughRepository
        this.vodPartRepository = vodPartRepository
        this.platformRepository = platformRepository
        gameCompletedItemModelMapper = GameCompletedItemModelMapper()
    }
}