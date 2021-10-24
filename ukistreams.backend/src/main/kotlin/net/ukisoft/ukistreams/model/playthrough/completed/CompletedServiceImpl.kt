package net.ukisoft.ukistreams.model.playthrough.completed

import net.ukisoft.ukistreams.entities.Game
import net.ukisoft.ukistreams.entities.Platform
import net.ukisoft.ukistreams.entities.Playthrough
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
            .groupBy { it.key.playthrough!!.id }

        val gameMap = playthroughs
            .groupBy { it.game!! }
            .map { x -> x.key to x.value.maxByOrNull { it.endDate!! } }
            .filter { (_, value) -> value != null }
            .map { (key, value) -> key to value!! }

        val items = gameMap
            .sortedWith(compareBy<Pair<Game, Playthrough>> { it.second.endDate }
                .thenBy { it.second.id })
            .map { (key, value) ->
                gameCompletedItemModelMapper.toModel(
                    key,
                    value,
                    playthroughVodGroup[value.id]?.map { it.toPair() } ?: ArrayList())
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
            .groupBy { it.vod!! }
        val filter = RepositoryFilter()
        filter.fetchFields =
            listOf(FetchField.left("game"), FetchField.left("game.genre"), FetchField.left("game.review"))
        val playthroughs = playthroughRepository.findByFilter(filter)
        val platforms = platformRepository.findAll(Sort.by(Sort.Direction.ASC, "ordinal"))

        val playthroughVodGroup = vodGroup.entries
            .groupBy { it.key.playthrough!!.id!! }
        val gameMap = playthroughs
            .groupBy { it.game!! }
            .map { x -> x.key to x.value.maxByOrNull { it.endDate!! } }
            .filter { (_, value) -> value != null }
            .map { (key, value) -> key to value!! }

        val gameByPlatformGroup = gameMap
            .filter { it.first.platform != null }
            .groupBy { it.first.platform!!.id!! }

        val gamePlatforms = ArrayList<GameCompletedPlatformModel>()
        val mapper = GameCompletedByPlatformItemModelMapper()
        platforms.forEach { platform: Platform ->
            val list = gameByPlatformGroup[platform.id] ?: ArrayList()
            val games = list
                .sortedBy { it.first.name }
                .map {
                    mapper.toModel(
                        it.first,
                        it.second,
                        playthroughVodGroup[it.second.id]?.map { x -> x.toPair() } ?: ArrayList()
                    )
                }
            val model = GameCompletedPlatformModel(platform.shortName!!, games)
            gamePlatforms.add(model)
        }
        return gamePlatforms
    }

    init {
        this.playthroughRepository = playthroughRepository
        this.vodPartRepository = vodPartRepository
        this.platformRepository = platformRepository
        gameCompletedItemModelMapper = GameCompletedItemModelMapper()
    }
}