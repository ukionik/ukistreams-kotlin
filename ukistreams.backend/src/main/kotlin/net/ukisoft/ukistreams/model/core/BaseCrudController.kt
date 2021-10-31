package net.ukisoft.ukistreams.model.core

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 29.08.2020 19:42
 */
abstract class BaseCrudController<TEditModel : BaseEditModel> protected constructor(service: CrudService<TEditModel>) {
    protected open val service: CrudService<TEditModel>

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<TEditModel> {
        val item = service.findById(id)
        return ResponseEntity.ok(item)
    }

    @PostMapping("create")
    fun create(@RequestBody model: TEditModel): ResponseEntity<Long> {
        val id = service.create(model)
        return ResponseEntity.ok(id)
    }

    @PutMapping("update")
    fun update(@RequestBody model: TEditModel): ResponseEntity<Void> {
        service.update(model)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping("delete/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }

    init {
        this.service = service
    }
}