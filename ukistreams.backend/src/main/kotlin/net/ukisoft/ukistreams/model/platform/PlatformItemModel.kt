package net.ukisoft.ukistreams.model.platform

import net.ukisoft.ukistreams.model.core.BaseItemModel

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 22.08.2020 01:52
 */
class PlatformItemModel(
    val id: Long,
    val ordinal: Int,
    val name: String,
    val shortName: String
) : BaseItemModel()