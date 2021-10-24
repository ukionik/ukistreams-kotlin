package net.ukisoft.ukistreams.model.core

import net.ukisoft.ukistreams.model.core.BaseCrudController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import java.lang.Void
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 07:30
 */
abstract class BaseCrudAllController<TEditModel : BaseEditModel?, TItemModel : BaseItemModel?> protected constructor(
    service: CrudAllService<TEditModel, TItemModel>
) : BaseCrudController<TEditModel>(service) {
    protected override val service: CrudAllService<TEditModel, TItemModel>
    @GetMapping("list")
    fun list(): ResponseEntity<List<TItemModel>> {
        val items: Unit = service.findAll()
        return ResponseEntity.ok(items)
    }

    init {
        this.service = service
    }
}