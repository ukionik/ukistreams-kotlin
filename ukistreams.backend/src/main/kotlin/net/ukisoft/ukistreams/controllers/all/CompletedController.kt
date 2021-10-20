package net.ukisoft.ukistreams.controllers.all

import net.ukisoft.ukistreams.model.playthrough.completed.CompletedService
import net.ukisoft.ukistreams.model.playthrough.completed.GameCompletedItemModel
import net.ukisoft.ukistreams.model.playthrough.completed.GameCompletedPlatformModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 25.10.2020 04:29
 */
@RestController
@RequestMapping("/api/v1/completed")
class CompletedController @Autowired constructor(completedService: CompletedService) {
    private val completedService: CompletedService

    @GetMapping("all")
    fun all(): ResponseEntity<List<GameCompletedItemModel>> {
        val items: List<GameCompletedItemModel> = completedService.findAll()
        return ResponseEntity.ok(items)
    }

    @GetMapping("by-platform")
    fun byPlatform(): ResponseEntity<List<GameCompletedPlatformModel>> {
        val items: List<GameCompletedPlatformModel> = completedService.findByPlatform()
        return ResponseEntity.ok(items)
    }

    init {
        this.completedService = completedService
    }
}