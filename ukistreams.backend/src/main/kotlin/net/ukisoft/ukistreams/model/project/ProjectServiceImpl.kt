package net.ukisoft.ukistreams.model.project

import net.ukisoft.ukistreams.entities.Project
import net.ukisoft.ukistreams.model.core.CrudAllServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 11:53
 */
@Service
@Transactional
class ProjectServiceImpl @Autowired protected constructor(projectRepository: ProjectRepository) :
    CrudAllServiceImpl<Project, ProjectEditModel, ProjectItemModel>(
        projectRepository,
        ProjectEditModelMapper(),
        ProjectItemModelMapper()
    ), ProjectService {
    override fun defaultSort(): Sort {
        return Sort.by(Sort.Direction.ASC, "name")
    }
}