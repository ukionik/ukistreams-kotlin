package net.ukisoft.ukistreams.model.items

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 17.09.2020 21:48
 */
interface ItemService {
    fun findGames(): List<SelectItemModel<Long>>
    fun findPlatforms(): List<SelectItemModel<Long>>
    fun findGenres(): List<SelectItemModel<Long>>
    fun findRegions(): List<SelectItemModel<String>>
    fun findProjects(): List<SelectItemModel<Long>>
    fun findFirstPlaythrough(): List<SelectItemModel<String>>
    fun findBlind(): List<SelectItemModel<String>>
    fun findVodTypes(): List<SelectItemModel<String>>
}