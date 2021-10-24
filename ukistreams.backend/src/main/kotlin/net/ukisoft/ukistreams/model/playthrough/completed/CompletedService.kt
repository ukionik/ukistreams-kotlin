package net.ukisoft.ukistreams.model.playthrough.completed

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 25.10.2020 02:53
 */
interface CompletedService {
    fun findAll(): List<GameCompletedItemModel>
    fun findByPlatform(): List<GameCompletedPlatformModel>
}