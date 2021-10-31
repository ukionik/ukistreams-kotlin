package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.entities.Playthrough
import net.ukisoft.ukistreams.model.core.FetchField
import net.ukisoft.ukistreams.model.core.RepositoryFilter
import net.ukisoft.ukistreams.model.game.GameRepository
import net.ukisoft.ukistreams.model.genre.GenreRepository
import net.ukisoft.ukistreams.model.platform.PlatformRepository
import net.ukisoft.ukistreams.model.project.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:22
 */
@Service
@Transactional
class PlaythroughServiceImpl @Autowired constructor(
    private val playthroughRepository: PlaythroughRepository,
    platformRepository: PlatformRepository,
    genreRepository: GenreRepository,
    gameRepository: GameRepository,
    projectRepository: ProjectRepository,
    vodRepository: VodRepository,
    private val vodPartRepository: VodPartRepository
) : PlaythroughService {
    private val editModelMapper: PlaythroughEditModelMapper
    private val playthroughItemModelMapper: PlaythroughItemModelMapper
    override fun findById(id: Long): PlaythroughEditModel {
        val entity: Playthrough = playthroughRepository.getById(id)
        return editModelMapper.toModel(entity)
    }

    override fun create(model: PlaythroughEditModel): Long {
        val entity = editModelMapper.toNewEntity(model)
        playthroughRepository.save(entity)
        return entity.id!!
    }

    override fun update(model: PlaythroughEditModel) {
        val entity: Playthrough = playthroughRepository.getById(model.id!!)
        editModelMapper.updateEntity(entity, model)
        playthroughRepository.save<Playthrough>(entity)
    }

    override fun delete(id: Long) {
        playthroughRepository.deleteById(id)
    }

    override fun findAll(): List<PlaythroughItemModel> {
        val vodPartRepositoryFilter = RepositoryFilter()
        vodPartRepositoryFilter.fetchFields = listOf(FetchField.inner("vod"))
        val vodGroup = vodPartRepository.findByFilter(vodPartRepositoryFilter)
            .groupBy { it.vod!! }

        val playthroughVodGroup = vodGroup.entries
            .groupBy { it.key.playthrough!!.id!! }

        val filter = RepositoryFilter()
        filter.fetchFields = listOf(
            FetchField.left("game"),
            FetchField.left("game.platform"),
            FetchField.left("game.genre"),
            FetchField.left("game.review"),
            FetchField.left("project")
        )
        val playthroughs: List<Playthrough> = playthroughRepository.findByFilter(filter)
        val items = playthroughs
            .map {
                playthroughItemModelMapper.toModel(
                    it,
                    playthroughVodGroup[it.id]?.map { x -> x.key to x.value } ?: ArrayList()
                )
            }
            .sortedWith(
                compareBy<PlaythroughItemModel> { it.endDate }.reversed()
                    .thenComparing(compareBy<PlaythroughItemModel> { it.id }.reversed())
            )

        for (i in items.indices) {
            val item = items[i]
            item.index = items.size - i
        }
        return items
    }

    init {
        editModelMapper = PlaythroughEditModelMapper(
            platformRepository, genreRepository, gameRepository, projectRepository, vodRepository, vodPartRepository
        )
        playthroughItemModelMapper = PlaythroughItemModelMapper()
    }
}