package net.ukisoft.ukistreams.controllers.all

import net.ukisoft.ukistreams.model.v1.all.completed.GameCompletedItemModel
import org.springframework.beans.factory.annotation.Autowired
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
        val items: Unit = completedService.findAll()
        return ResponseEntity.ok<List<GameCompletedItemModel>>(items)
    }

    @GetMapping("by-platform")
    fun byPlatform(): ResponseEntity<List<GameCompletedPlatformModel>> {
        val items: Unit = completedService.findByPlatform()
        return ResponseEntity.ok<List<GameCompletedPlatformModel>>(items)
    }

    init {
        this.completedService = completedService
    }
}