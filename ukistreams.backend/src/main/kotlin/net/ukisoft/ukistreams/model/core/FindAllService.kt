package net.ukisoft.ukistreams.model.core

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 05:57
 */
interface FindAllService<TItemModel : BaseItemModel> {
    fun findAll(): List<TItemModel>
}