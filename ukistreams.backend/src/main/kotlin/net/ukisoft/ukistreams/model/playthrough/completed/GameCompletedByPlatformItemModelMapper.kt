package net.ukisoft.ukistreams.model.playthrough.completed

import net.ukisoft.ukistreams.entities.Game
import net.ukisoft.ukistreams.entities.Playthrough
import net.ukisoft.ukistreams.entities.Vod
import net.ukisoft.ukistreams.entities.VodPart

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 19:43
 */
class GameCompletedByPlatformItemModelMapper {
    fun toModel(
        game: Game,
        playthrough: Playthrough,
        vodGroup: List<Pair<Vod, List<VodPart>>>
    ): GameCompletedByPlatformItemModel {
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

        return GameCompletedByPlatformItemModel(
            game.name!!,
            game.genre!!.id!!,
            game.genre!!.name!!,
            playthrough.duration!!,
            playthrough.endDate!!.toLocalDate(),
            game.review!!.rate,
            game.review!!.difficulty,
            playthrough.pickedBy!!,
            vodParts
        )
    }
}