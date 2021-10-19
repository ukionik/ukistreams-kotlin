package net.ukisoft.ukistreams.model.playthrough.completed

import net.ukisoft.ukistreams.entity.Game
import java.util.ArrayList
import java.util.Comparator
import java.util.function.Consumer
import java.util.function.Function

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 25.10.2020 03:20
 */
class GameCompletedItemModelMapper {
    fun toModel(
        game: Game,
        playthrough: Playthrough,
        vodGroup: List<Map.Entry<Vod, List<VodPart?>?>>
    ): GameCompletedItemModel {
        val model = GameCompletedItemModel()
        model.setGameName(game.getName())
        model.setPlatformId(game.getPlatform().getId())
        model.setPlatformName(game.getPlatform().getShortName())
        model.setGenreId(game.getGenre().getId())
        model.setGenreName(game.getGenre().getName())
        model.setProjectId(playthrough.getProject().getId())
        model.setProjectName(playthrough.getProject().getName())
        model.setDuration(playthrough.getDuration())
        model.setEndDate(playthrough.getEndDate().toLocalDate())
        model.setRate(game.getReview().getRate())
        model.setDifficulty(game.getReview().getDifficulty())
        model.setPickedBy(playthrough.getPickedBy())
        val vodParts: MutableList<GameCompletedVodPartItemModel> = ArrayList<GameCompletedVodPartItemModel>()
        vodGroup.stream()
            .sorted(
                Comparator.comparing<Map.Entry<Vod, List<VodPart?>?>, Any>(
                    Function<Map.Entry<Vod, List<VodPart?>?>, Any> { (key): Map.Entry<Vod, List<VodPart?>?> -> key.getType() })
            )
            .map<List<VodPart>>(Function<Map.Entry<Vod, List<VodPart?>?>, List<VodPart>> { (key, value) -> java.util.Map.Entry.value })
            .findFirst()
            .orElse(ArrayList<VodPart>())
            .forEach(Consumer<VodPart> { x: VodPart ->
                val vodPartModel = GameCompletedVodPartItemModel()
                vodPartModel.setType(x.getVod().getType().name())
                vodPartModel.setOrdinal(x.getOrdinal())
                vodPartModel.setUrl(x.getUrl())
                vodParts.add(vodPartModel)
            })
        model.setVodParts(vodParts)
        return model
    }
}