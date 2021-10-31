package net.ukisoft.ukistreams.controllers.admin

import net.ukisoft.ukistreams.model.core.BaseCrudAllController
import net.ukisoft.ukistreams.model.project.ProjectEditModel
import net.ukisoft.ukistreams.model.project.ProjectItemModel
import net.ukisoft.ukistreams.model.project.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 12:00
 */
@RestController
@RequestMapping("/api/admin/project")
class ProjectController @Autowired protected constructor(projectService: ProjectService) :
    BaseCrudAllController<ProjectEditModel, ProjectItemModel>(projectService)