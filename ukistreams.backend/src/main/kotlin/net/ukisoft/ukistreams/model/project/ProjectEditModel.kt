package net.ukisoft.ukistreams.model.project

import net.ukisoft.ukistreams.model.core.BaseEditModel

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 11:54
 */
data class ProjectEditModel(
    override val id: Long?,
    val name: String
) : BaseEditModel()