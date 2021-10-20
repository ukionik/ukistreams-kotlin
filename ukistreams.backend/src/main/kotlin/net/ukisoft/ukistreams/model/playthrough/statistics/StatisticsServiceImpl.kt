package net.ukisoft.ukistreams.model.playthrough.statistics

import net.ukisoft.ukistreams.model.core.FetchField
import net.ukisoft.ukistreams.model.core.RepositoryFilter
import net.ukisoft.ukistreams.model.genre.GenreRepository
import net.ukisoft.ukistreams.model.platform.PlatformRepository
import net.ukisoft.ukistreams.model.playthrough.PlaythroughRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 01:14
 */
@Service
@Transactional
class StatisticsServiceImpl @Autowired constructor(
    playthroughRepository: PlaythroughRepository,
    platformRepository: PlatformRepository,
    genreRepository: GenreRepository
) : StatisticsService {
    private val playthroughRepository: PlaythroughRepository
    private val platformRepository: PlatformRepository
    private val genreRepository: GenreRepository
    override fun findStatistics(): StatisticsModel {
        val filter = RepositoryFilter()
        filter.fetchFields = listOf(FetchField.left("game"))
        val playthroughs = playthroughRepository.findByFilter(filter)
        val platforms = platformRepository.findAll(Sort.by(Sort.Direction.ASC, "ordinal"))
        val genres = genreRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
        val gameMap = playthroughs
            .groupBy { it.game!! }
            .map { x -> x.key to x.value.maxByOrNull { it.endDate!! } }
            .filter { (_, value) -> value != null }
            .map { (key, value) -> key to value!! }

        val platformMapper = StatisticsPlatformModelMapper()
        val genreMapper = StatisticsGenreModelMapper()
        val platformMap = gameMap.groupBy { it.first.platform!!.id!! }
        val genreMap = gameMap.groupBy { it.first.genre!!.id!! }

        return StatisticsModel(platformMapper.toModel(platforms, platformMap), genreMapper.toModel(genres, genreMap))
    }

    init {
        this.playthroughRepository = playthroughRepository
        this.platformRepository = platformRepository
        this.genreRepository = genreRepository
    }
}