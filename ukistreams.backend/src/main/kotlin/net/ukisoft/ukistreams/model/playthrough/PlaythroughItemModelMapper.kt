package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.entities.Playthrough
import net.ukisoft.ukistreams.entities.Vod
import net.ukisoft.ukistreams.entities.VodPart

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:24
 */
class PlaythroughItemModelMapper {
    fun toModel(
        entity: Playthrough, vodGroup: List<Pair<Vod, List<VodPart>>>
    ): PlaythroughItemModel {
        val vods = ArrayList<PlaythroughVodItemModel>()
        vodGroup
            .sortedBy { it.first.type }
            .forEach { (vod, parts) ->
                val vodParts = ArrayList<PlaythroughVodPartItemModel>()
                parts
                    .sortedBy { it.ordinal }
                    .forEach {
                        val partModel = PlaythroughVodPartItemModel(
                            it.id!!,
                            it.ordinal!!,
                            it.url!!
                        )
                        vodParts.add(partModel)
                    }

                val vodModel = PlaythroughVodItemModel(
                    vod.id!!,
                    vod.type!!.name,
                    vodParts
                )

                vods.add(vodModel)
            }

        return PlaythroughItemModel(
            entity.id!!,
            entity.game!!.name!!,
            entity.game!!.platform!!.shortName!!,
            entity.game!!.genre!!.name!!,
            entity.game!!.review!!.rate,
            entity.game!!.review!!.difficulty,
            entity.region!!.name,
            entity.duration!!,
            entity.startDate!!.toLocalDate(),
            entity.endDate!!.toLocalDate(),
            entity.pickedBy!!,
            entity.project!!.name!!,
            entity.firstPlaythrough!!.label,
            entity.blind!!.label,
            vods,
            entity.comment!!
        )
    }
}