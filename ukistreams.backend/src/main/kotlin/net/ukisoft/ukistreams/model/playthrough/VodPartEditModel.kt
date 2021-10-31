package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.model.core.BaseEditModel

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 21.09.2020 23:09
 */
data class VodPartEditModel(
    override var id: Long?,
    var ordinal: Int,
    var url: String
) : BaseEditModel()