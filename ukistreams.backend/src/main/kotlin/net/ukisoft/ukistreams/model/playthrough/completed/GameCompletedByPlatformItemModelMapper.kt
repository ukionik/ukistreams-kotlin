package net.ukisoft.ukistreams.model.playthrough.completed

import net.ukisoft.ukistreams.entity.Game
import java.util.ArrayList
import java.util.Comparator
import java.util.function.Consumer
import java.util.function.Function

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 19:43
 */
class GameCompletedByPlatformItemModelMapper {
    fun toModel(
        game: Game,
        playthrough: Playthrough,
        vodGroup: List<Map.Entry<Vod, List<VodPart?>?>>
    ): GameCompletedByPlatformItemModel {
        val model = GameCompletedByPlatformItemModel()
        model.setGameName(game.getName())
        model.setGenreId(game.getGenre().getId())
        model.setGenreName(Util.getSafeValue { game.getGenre().getName() })
        model.setDuration(playthrough.getDuration())
        if (playthrough.getEndDate() != null) {
            model.setEndDate(playthrough.getEndDate().toLocalDate())
        }
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