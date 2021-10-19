package net.ukisoft.ukistreams.model.playthrough.completed

import java.time.Duration
import java.time.LocalDate

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 25.10.2020 02:55
 */
class GameCompletedItemModel {
    var index: Int? = null
    var gameName: String? = null
    var platformId: Long? = null
    var platformName: String? = null
    var genreId: Long? = null
    var genreName: String? = null

    ///============================== Getters / Setters ==============================
    var projectId: Long? = null
    var projectName: String? = null
    var duration: Duration? = null
    var endDate: LocalDate? = null
    var rate: Double? = null
    var difficulty: Double? = null
    var pickedBy: String? = null

    ///============================== End of Getters / Setters ==============================
    var vodParts: List<GameCompletedVodPartItemModel>? = null
}