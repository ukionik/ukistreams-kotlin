package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.entity.VodPart
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.ArrayList
import java.util.Comparator
import java.util.function.Function

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:22
 */
@Service
@Transactional
class PlaythroughServiceImpl @Autowired constructor(
    private val playthroughRepository: PlaythroughRepository,
    platformRepository: PlatformRepository?,
    genreRepository: GenreRepository?,
    gameRepository: GameRepository?,
    projectRepository: ProjectRepository?,
    vodRepository: VodRepository?,
    private val vodPartRepository: VodPartRepository
) : PlaythroughService {
    private val editModelMapper: PlaythroughEditModelMapper
    private val playthroughItemModelMapper: PlaythroughItemModelMapper
    fun findById(id: Long): PlaythroughEditModel {
        val entity: Playthrough = playthroughRepository.getOne(id)
        return editModelMapper.toModel(entity)
    }

    fun create(model: PlaythroughEditModel?): Long {
        val entity: Unit = editModelMapper.toNewEntity(model)
        playthroughRepository.save(entity)
        return entity.getId()
    }

    fun update(model: PlaythroughEditModel) {
        val entity: Playthrough = playthroughRepository.getOne(model.id)
        editModelMapper.updateEntity(entity, model)
        playthroughRepository.save<Playthrough>(entity)
    }

    fun delete(id: Long) {
        playthroughRepository.deleteById(id)
    }

    fun findAll(): List<PlaythroughItemModel> {
        val vodPartRepositoryFilter = RepositoryFilter()
        vodPartRepositoryFilter.setFetchFields(FetchField.left("vod"))
        val vodGroup: Map<Any, List<VodPart>> = vodPartRepository.findByFilter(vodPartRepositoryFilter)
            .parallelStream()
            .collect(Collectors.groupingBy<Any, Any>(VodPart::getVod))
        val playthroughVodGroup: Map<Any, List<Map.Entry<Any, List<VodPart>>>> = vodGroup.entries
            .parallelStream()
            .collect(
                Collectors.groupingBy<Any, Any>(
                    Function<Any, Any> { (key): Map.Entry<Any, List<VodPart?>?> -> key.getPlaythrough().getId() })
            )
        val filter = RepositoryFilter()
        filter.setFetchFields(
            FetchField.left("game"),
            FetchField.left("game.platform"),
            FetchField.left("game.genre"),
            FetchField.left("game.review"),
            FetchField.left("project")
        )
        val playthroughs: List<Playthrough> = playthroughRepository.findByFilter(filter)
        val items = playthroughs.parallelStream()
            .map(Function<Playthrough, Any> { x: Playthrough ->
                playthroughItemModelMapper.toModel(
                    x,
                    playthroughVodGroup[x.id] ?: ArrayList<Map.Entry<Any, List<VodPart>>>()
                )
            })
            .sorted(
                Comparator.comparing<Any, Any>(PlaythroughItemModel::getEndDate).reversed()
                    .thenComparing(Comparator.comparing<Any, Any>(PlaythroughItemModel::getId).reversed())
            )
            .collect(Collectors.toList<Any>())
        for (i in items.indices) {
            val item = items[i]
            item.setIndex(items.size - i)
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