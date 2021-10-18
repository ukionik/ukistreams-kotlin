package net.ukisoft.ukistreams.controllers

import net.ukisoft.ukistreams.model.test.TestModel
import net.ukisoft.ukistreams.model.test.TestSimpleModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 29.08.2020 05:33
 */
@RestController
@RequestMapping("/api/test")
class TestController {
    @GetMapping("get")
    fun get(): ResponseEntity<TestModel> {
        val model = TestModel(
            "Hello World!",
            Duration.ofSeconds(24 * 60 * 60 + 15 * 60 * 60 + 22 * 60 + 38),
            Duration.ofMillis(60 * 22 + 123),
            LocalDateTime.now(),
            LocalDate.now()
        )
        return ResponseEntity.ok(model)
    }

    @PutMapping("set")
    fun set(@RequestBody model: TestModel): ResponseEntity<TestModel> {
        return ResponseEntity<TestModel>(model, HttpStatus.OK)
    }

    @PutMapping("set-simple")
    fun setSimple(@RequestBody model: TestSimpleModel): ResponseEntity<TestSimpleModel> {
        return ResponseEntity<TestSimpleModel>(model, HttpStatus.OK)
    }

}