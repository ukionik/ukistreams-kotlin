package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.enums.VodType

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:39
 */
class NewGameModel {
    ///============================== Getters / Setters ==============================
    var name: String? = null
    var platformId: Long? = null
    var genreId: Long? = null
    var rate: Double? = null

    ///============================== End of Getters / Setters ==============================
    var difficulty: Double? = null
}