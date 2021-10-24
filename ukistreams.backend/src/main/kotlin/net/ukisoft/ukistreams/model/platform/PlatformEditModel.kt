package net.ukisoft.ukistreams.model.platform

import net.ukisoft.ukistreams.model.core.BaseEditModel

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 22.08.2020 01:51
 */
data class PlatformEditModel(
    override val id: Long,
    val ordinal: Int,
    val name: String,
    val shortName: String
) : BaseEditModel()