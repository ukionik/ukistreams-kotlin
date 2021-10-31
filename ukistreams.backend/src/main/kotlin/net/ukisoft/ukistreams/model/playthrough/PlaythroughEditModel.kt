package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.enums.Blind
import net.ukisoft.ukistreams.enums.FirstPlaythrough
import net.ukisoft.ukistreams.enums.Region
import net.ukisoft.ukistreams.model.core.BaseEditModel
import java.time.Duration
import java.time.LocalDate

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:22
 */
data class PlaythroughEditModel(
    override val id: Long?,
    val newGame: Boolean,
    val gameId: Long,
    val newGameInfo: NewGameModel?,
    val region: Region,
    val duration: Duration,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val pickedBy: String,
    val projectId: Long,
    val firstPlaythrough: FirstPlaythrough,
    val blind: Blind,
    val comment: String,
    val vods: List<VodEditModel>
) : BaseEditModel()