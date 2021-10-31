package net.ukisoft.ukistreams.model.project

import net.ukisoft.ukistreams.entities.Project
import net.ukisoft.ukistreams.model.core.EditModelMapper

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 11:54
 */
class ProjectEditModelMapper : EditModelMapper<Project, ProjectEditModel> {
    override fun toModel(entity: Project): ProjectEditModel = ProjectEditModel(
        entity.id!!,
        entity.name!!
    )

    override fun toNewEntity(model: ProjectEditModel): Project {
        val entity = Project()
        updateEntity(entity, model)
        return entity
    }

    override fun updateEntity(entity: Project, model: ProjectEditModel) {
        entity.name = model.name
    }
}