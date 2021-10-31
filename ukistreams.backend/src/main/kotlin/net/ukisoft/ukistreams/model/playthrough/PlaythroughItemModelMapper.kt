package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.entity.Playthrough
import java.util.ArrayList
import java.util.Comparator
import java.util.function.Consumer
import java.util.function.Function

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:24
 */
class PlaythroughItemModelMapper {
    fun toModel(
        entity: Playthrough, vodGroup: List<Map.Entry<Vod, List<VodPart>>>
    ): PlaythroughItemModel {
        val model = PlaythroughItemModel()
        model.id = entity.getId()
        model.gameName = entity.getGame().getName()
        model.platformName = entity.getGame().getPlatform().getShortName()
        model.genreName = entity.getGame().getGenre().getName()
        model.rate = entity.getGame().getReview().getRate()
        model.difficulty = entity.getGame().getReview().getDifficulty()
        model.region = entity.getRegion().name()
        model.duration = entity.getDuration()
        model.startDate = entity.getStartDate().toLocalDate()
        model.endDate = entity.getEndDate().toLocalDate()
        model.pickedBy = entity.getPickedBy()
        model.projectName = entity.getProject().getName()
        model.firstPlaythrough = entity.getFirstPlaythrough().getLabel()
        model.blind = entity.getBlind().getLabel()
        model.comment = entity.getComment()
        val vods: MutableList<PlaythroughVodItemModel> = ArrayList<PlaythroughVodItemModel>()
        vodGroup.stream()
            .sorted(
                Comparator.comparing<Map.Entry<Vod, List<VodPart>>, Any>(
                    Function<Map.Entry<Vod, List<VodPart>>, Any> { (key): Map.Entry<Vod, List<VodPart>> -> key.getType() })
            )
            .forEach { (vod, value): Map.Entry<Vod, List<VodPart>> ->
                val vodModel = PlaythroughVodItemModel()
                vodModel.id = vod.getId()
                vodModel.type = vod.getType().name()
                vods.add(vodModel)
                val parts: MutableList<PlaythroughVodPartItemModel> = ArrayList<PlaythroughVodPartItemModel>()
                value.stream()
                    .sorted(Comparator.comparing<VodPart, Any>(VodPart::getOrdinal))
                    .forEach(Consumer<VodPart> { part: VodPart ->
                        val partModel = PlaythroughVodPartItemModel()
                        partModel.id = part.getId()
                        partModel.ordinal = part.getOrdinal()
                        partModel.url = part.getUrl()
                        parts.add(partModel)
                    })
                vodModel.parts = parts
            }
        model.vods = vods
        return model
    }
}