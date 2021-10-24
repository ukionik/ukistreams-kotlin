package net.ukisoft.ukistreams.model.playthrough.completed

import net.ukisoft.ukistreams.entities.Game
import net.ukisoft.ukistreams.entities.Playthrough
import net.ukisoft.ukistreams.entities.Vod
import net.ukisoft.ukistreams.entities.VodPart

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 25.10.2020 03:20
 */
class GameCompletedItemModelMapper {
    fun toModel(
        game: Game,
        playthrough: Playthrough,
        vodGroup: List<Pair<Vod, List<VodPart>>>
    ): GameCompletedItemModel {
        val vodParts: MutableList<GameCompletedVodPartItemModel> = ArrayList()
        vodGroup.sortedBy { x -> x.first.type }
            .map { x -> x.second }
            .firstOrNull()
            .orEmpty()
            .forEach { x ->
                vodParts.add(
                    GameCompletedVodPartItemModel(
                        x.vod!!.type!!.name,
                        x.ordinal!!,
                        x.url!!
                    )
                )
            }

        return GameCompletedItemModel(
            game.name!!,
            game.platform!!.id!!,
            game.platform!!.shortName!!,
            game.genre!!.id!!,
            game.genre!!.name!!,
            playthrough.project!!.id!!,
            playthrough.project!!.name!!,
            playthrough.duration!!,
            playthrough.endDate!!.toLocalDate(),
            game.review!!.rate,
            game.review!!.difficulty,
            playthrough.pickedBy!!,
            vodParts
        )
    }
}