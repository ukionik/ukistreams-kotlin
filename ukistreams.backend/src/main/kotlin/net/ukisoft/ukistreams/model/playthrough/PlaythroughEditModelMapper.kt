package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.core.EditModelMapper
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Predicate

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
    vodPartRepository: VodPartRepository?
) : EditModelMapper<Playthrough?, PlaythroughEditModel?> {
    private val platformRepository: PlatformRepository
    private val genreRepository: GenreRepository
    private val gameRepository: GameRepository
    private val projectRepository: ProjectRepository
    private val vodRepository: VodRepository
    private val vodMapper: VodEditModelMapper
    fun toModel(entity: Playthrough): PlaythroughEditModel {
        val model = PlaythroughEditModel()
        model.id = entity.getId()
        model.gameId = Util.getSafeValue { entity.getGame().getId() }
        model.region = entity.getRegion()
        model.duration = entity.getDuration()
        model.startDate = entity.getStartDate().toLocalDate()
        model.endDate = entity.getEndDate().toLocalDate()
        model.pickedBy = entity.getPickedBy()
        model.projectId = Util.getSafeValue { entity.getProject().getId() }
        model.firstPlaythrough = entity.getFirstPlaythrough()
        model.blind = entity.getBlind()
        model.comment = entity.getComment()
        model.vods = entity.getVods()
            .stream()
            .map(vodMapper::toModel)
            .collect(Collectors.toList())
        return model
    }

    fun updateEntity(entity: Playthrough, model: PlaythroughEditModel) {
        entity.setRegion(model.region)
        entity.setDuration(model.duration)
        entity.setStartDate(model.startDate.atStartOfDay())
        entity.setEndDate(model.endDate.atStartOfDay())
        entity.setPickedBy(model.pickedBy)
        entity.setProject(projectRepository.getOne(model.projectId))
        entity.setFirstPlaythrough(model.firstPlaythrough)
        entity.setBlind(model.blind)
        entity.setComment(model.comment)
        val vodMap: Map<Long, VodEditModel> = model.vods.stream()
            .filter(Predicate { x: VodEditModel? -> x.id != null })
            .collect(Collectors.toMap<Any, Any, Any>(VodEditModel::getId, Function { x: Any? -> x }))
        entity.getVods().stream()
            .filter { x -> !vodMap.containsKey(x.getId()) }
            .forEach(entity::removeVod)
        model.vods.stream()
            .filter(Predicate { x: VodEditModel? -> x.id == null })
            .forEach(Consumer { vodModel: VodEditModel? ->
                val vodEntity: Unit = vodMapper.toNewEntity(vodModel)
                entity.addVod(vodEntity)
                vodRepository.save(vodEntity)
            })
        entity.getVods().stream()
            .filter { x -> vodMap.containsKey(x.getId()) }
            .forEach { vodEntity ->
                val vodModel: VodEditModel? = vodMap[vodEntity.getId()]
                vodMapper.updateEntity(vodEntity, vodModel)
                vodRepository.save(vodEntity)
            }
    }

    fun toNewEntity(model: PlaythroughEditModel): Playthrough {
        val playthrough = Playthrough()
        if (model.isNewGame) {
            val gameInfo: NewGameModel? = model.newGameInfo
            val game = Game()
            game.setName(gameInfo.name)
            game.setPlatform(platformRepository.getOne(gameInfo.platformId))
            game.setGenre(genreRepository.getOne(gameInfo.genreId))
            val gameReview = GameReview()
            gameReview.setDifficulty(gameInfo.difficulty)
            gameReview.setRate(gameInfo.rate)
            game.setReview(gameReview)
            gameRepository.save(game)
            playthrough.setGame(game)
        } else {
            playthrough.setGame(gameRepository.getOne(model.gameId))
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