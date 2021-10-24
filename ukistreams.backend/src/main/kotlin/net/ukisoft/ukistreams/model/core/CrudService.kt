package net.ukisoft.ukistreams.model.core

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 05:04
 */
interface CrudService<TEditModel : BaseEditModel> {
    fun findById(id: Long): TEditModel
    fun create(model: TEditModel): Long
    fun update(model: TEditModel)
    fun delete(id: Long)
}