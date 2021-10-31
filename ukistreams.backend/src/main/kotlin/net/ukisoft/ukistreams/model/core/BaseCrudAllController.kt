package net.ukisoft.ukistreams.model.core

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 07:30
 */
abstract class BaseCrudAllController<TEditModel : BaseEditModel, TItemModel : BaseItemModel> protected constructor(
    service: CrudAllService<TEditModel, TItemModel>
) : BaseCrudController<TEditModel>(service) {
    override val service: CrudAllService<TEditModel, TItemModel>

    @GetMapping("list")
    fun list(): ResponseEntity<List<TItemModel>> {
        val items = service.findAll()
        return ResponseEntity.ok(items)
    }

    init {
        this.service = service
    }
}