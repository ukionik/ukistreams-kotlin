package net.ukisoft.ukistreams.model.playthrough.completed

import net.ukisoft.ukistreams.entity.Platform

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 19:29
 */
class GameCompletedPlatformModel(platform: Platform, games: List<GameCompletedByPlatformItemModel>) {
    ///============================== Getters / Setters ==============================
    var platformName: String
    private var games: List<GameCompletedByPlatformItemModel>
    fun getGames(): List<GameCompletedByPlatformItemModel> {
        return games
    }

    fun setGames(games: List<GameCompletedByPlatformItemModel>) {
        this.games = games
    } ///============================== End of Getters / Setters ==============================

    init {
        platformName = Util.getSafeValue { platform.getShortName() }
        this.games = games
    }
}