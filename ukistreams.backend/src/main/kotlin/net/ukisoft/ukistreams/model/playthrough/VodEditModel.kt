package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.enums.VodType
import net.ukisoft.ukistreams.model.core.BaseEditModel

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 21.09.2020 23:08
 */
data class VodEditModel(
    override val id: Long?,
    val type: VodType,
    val parts: List<VodPartEditModel>
) : BaseEditModel()