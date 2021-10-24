package net.ukisoft.ukistreams.controllers.admin

import net.ukisoft.ukistreams.model.items.ItemService
import net.ukisoft.ukistreams.model.items.SelectItemModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 17.09.2020 21:43
 */
@RestController
@RequestMapping("/api/admin/items")
class AdminItemsController @Autowired constructor(itemService: ItemService) {
    private val itemService: ItemService

    @GetMapping("games")
    fun games(): ResponseEntity<List<SelectItemModel<Long>>> {
        val items = itemService.findGames()
        return ResponseEntity.ok(items)
    }

    @GetMapping("platforms")
    fun platforms(): ResponseEntity<List<SelectItemModel<Long>>> {
        val items = itemService.findPlatforms()
        return ResponseEntity.ok(items)
    }

    @GetMapping("genres")
    fun genres(): ResponseEntity<List<SelectItemModel<Long>>> {
        val items = itemService.findGenres()
        return ResponseEntity.ok(items)
    }

    @GetMapping("regions")
    fun regions(): ResponseEntity<List<SelectItemModel<String>>> {
        val items = itemService.findRegions()
        return ResponseEntity.ok(items)
    }

    @GetMapping("projects")
    fun projects(): ResponseEntity<List<SelectItemModel<Long>>> {
        val items = itemService.findProjects()
        return ResponseEntity.ok(items)
    }

    @GetMapping("first-playthrough")
    fun firstPlaythrough(): ResponseEntity<List<SelectItemModel<String>>> {
        val items = itemService.findFirstPlaythrough()
        return ResponseEntity.ok(items)
    }

    @GetMapping("blind")
    fun blind(): ResponseEntity<List<SelectItemModel<String>>> {
        val items = itemService.findBlind()
        return ResponseEntity.ok(items)
    }

    @GetMapping("vod-types")
    fun vodTypes(): ResponseEntity<List<SelectItemModel<String>>> {
        val items = itemService.findVodTypes()
        return ResponseEntity.ok(items)
    }

    init {
        this.itemService = itemService
    }
}