package net.ukisoft.ukistreams.model.items

import net.ukisoft.ukistreams.enums.Blind
import net.ukisoft.ukistreams.enums.FirstPlaythrough
import net.ukisoft.ukistreams.enums.Region
import net.ukisoft.ukistreams.enums.VodType
import net.ukisoft.ukistreams.model.game.GameRepository
import net.ukisoft.ukistreams.model.genre.GenreRepository
import net.ukisoft.ukistreams.model.platform.PlatformRepository
import net.ukisoft.ukistreams.model.project.ProjectRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 17.09.2020 21:48
 */
@Service
@Transactional
class ItemServiceImpl(
    gameRepository: GameRepository,
    platformRepository: PlatformRepository,
    genreRepository: GenreRepository,
    projectRepository: ProjectRepository
) : ItemService {
    private val gameRepository: GameRepository
    private val platformRepository: PlatformRepository
    private val genreRepository: GenreRepository
    private val projectRepository: ProjectRepository


    override fun findGames(): List<SelectItemModel<Long>> {
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
            .map { x ->
                SelectItemModel(x.id!!, "${x.name!!} (${x.platform!!.shortName!!})")
            }
    }

    override fun findPlatforms(): List<SelectItemModel<Long>> {
        return platformRepository.findAll(Sort.by(Sort.Direction.ASC, "shortName"))
            .map { x -> SelectItemModel(x.id!!, x.shortName!!) }
    }

    override fun findGenres(): List<SelectItemModel<Long>> {
        return genreRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
            .map { x -> SelectItemModel(x.id!!, x.name!!) }
    }

    override fun findRegions(): List<SelectItemModel<String>> {
        return Region.values()
            .map { x -> SelectItemModel(x.name, x.name) }
    }

    override fun findProjects(): List<SelectItemModel<Long>> {
        return projectRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
            .map { x -> SelectItemModel(x.id!!, x.name!!) }
    }

    override fun findFirstPlaythrough(): List<SelectItemModel<String>> {
        return FirstPlaythrough.values()
            .map { x -> SelectItemModel(x.name, x.label) }
    }

    override fun findBlind(): List<SelectItemModel<String>> {
        return Blind.values()
            .map { x -> SelectItemModel(x.name, x.label) }
    }

    override fun findVodTypes(): List<SelectItemModel<String>> {
        return VodType.values()
            .map { x -> SelectItemModel(x.name, x.name) }
    }

    init {
        this.gameRepository = gameRepository
        this.platformRepository = platformRepository
        this.genreRepository = genreRepository
        this.projectRepository = projectRepository
    }
}