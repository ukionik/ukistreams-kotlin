package net.ukisoft.ukistreams.controllers.all

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
 * Created: 07.11.2020 22:30
 */
@RestController
@RequestMapping("/api/v1/items")
class ItemsController @Autowired constructor(private val itemService: ItemService) {
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

    @GetMapping("projects")
    fun projects(): ResponseEntity<List<SelectItemModel<Long>>> {
        val items = itemService.findProjects()
        return ResponseEntity.ok(items)
    }
}