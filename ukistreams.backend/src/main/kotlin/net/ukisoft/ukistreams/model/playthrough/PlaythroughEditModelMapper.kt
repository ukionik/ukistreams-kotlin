package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.entities.Game
import net.ukisoft.ukistreams.entities.GameReview
import net.ukisoft.ukistreams.entities.Playthrough
import net.ukisoft.ukistreams.model.core.EditModelMapper
import net.ukisoft.ukistreams.model.game.GameRepository
import net.ukisoft.ukistreams.model.genre.GenreRepository
import net.ukisoft.ukistreams.model.platform.PlatformRepository
import net.ukisoft.ukistreams.model.project.ProjectRepository
import java.util.function.Consumer

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:24
 */
class PlaythroughEditModelMapper(
    platformRepository: PlatformRepository,
    genreRepository: GenreRepository,
    gameRepository: GameRepository,
    projectRepository: ProjectRepository,
    vodRepository: VodRepository,
    vodPartRepository: VodPartRepository
) : EditModelMapper<Playthrough, PlaythroughEditModel> {
    private val platformRepository: PlatformRepository
    private val genreRepository: GenreRepository
    private val gameRepository: GameRepository
    private val projectRepository: ProjectRepository
    private val vodRepository: VodRepository
    private val vodMapper: VodEditModelMapper
    override fun toModel(entity: Playthrough): PlaythroughEditModel = PlaythroughEditModel(
        entity.id,
        false,
        entity.game!!.id!!,
        null,
        entity.region!!,
        entity.duration!!,
        entity.startDate!!.toLocalDate(),
        entity.endDate!!.toLocalDate(),
        entity.pickedBy!!,
        entity.project!!.id!!,
        entity.firstPlaythrough!!,
        entity.blind!!,
        entity.comment,
        entity.vods
            .map(vodMapper::toModel)
    )

    override fun updateEntity(entity: Playthrough, model: PlaythroughEditModel) {
        entity.region = model.region
        entity.duration = model.duration
        entity.startDate = model.startDate.atStartOfDay()
        entity.endDate = model.endDate.atStartOfDay()
        entity.pickedBy = model.pickedBy
        entity.project = projectRepository.getById(model.projectId)
        entity.firstPlaythrough = model.firstPlaythrough
        entity.blind = model.blind
        entity.comment = model.comment

        val vodMap: Map<Long, VodEditModel> = model.vods
            .filter { it.id != null }
            .associateBy { it.id!! }

        entity.vods
            .filter { !vodMap.containsKey(it.id) }
            .forEach(entity::removeVod)

        model.vods
            .filter { it.id == null }
            .forEach(Consumer { vodModel ->
                val vodEntity = vodMapper.toNewEntity(vodModel)
                entity.addVod(vodEntity)
                vodRepository.save(vodEntity)
            })

        entity.vods
            .filter { vodMap.containsKey(it.id) }
            .forEach { vodEntity ->
                val vodModel = vodMap[vodEntity.id]!!
                vodMapper.updateEntity(vodEntity, vodModel)
                vodRepository.save(vodEntity)
            }
    }

    override fun toNewEntity(model: PlaythroughEditModel): Playthrough {
        val playthrough = Playthrough()
        if (model.newGame) {
            val gameInfo: NewGameModel? = model.newGameInfo
            val game = Game()
            game.name = gameInfo!!.name
            game.platform = platformRepository.getById(gameInfo.platformId!!)
            game.genre = genreRepository.getById(gameInfo.genreId!!)
            val gameReview = GameReview()
            gameReview.difficulty = gameInfo.difficulty
            gameReview.rate = gameInfo.rate
            game.review = gameReview
            gameRepository.save(game)
            playthrough.game = game
        } else {
            playthrough.game = gameRepository.getById(model.gameId)
        }
        updateEntity(playthrough, model)
        return playthrough
    }

    init {
        this.platformRepository = platformRepository
        this.genreRepository = genreRepository
        this.gameRepository = gameRepository
        this.projectRepository = projectRepository
        this.vodRepository = vodRepository
        vodMapper = VodEditModelMapper(vodPartRepository)
    }
}