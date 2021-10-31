package net.ukisoft.ukistreams.model.project

import net.ukisoft.ukistreams.entities.Project
import net.ukisoft.ukistreams.model.core.ItemModelMapper

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 11:54
 */
class ProjectItemModelMapper : ItemModelMapper<Project, ProjectItemModel> {
    override fun toModel(entity: Project): ProjectItemModel {
        return ProjectItemModel(
            entity.id!!,
            entity.name!!
        )
    }
}