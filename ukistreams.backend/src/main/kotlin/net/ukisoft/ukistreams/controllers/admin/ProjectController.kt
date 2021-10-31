package net.ukisoft.ukistreams.controllers.admin

import net.ukisoft.ukistreams.core.BaseCrudAllController
import net.ukisoft.ukistreams.model.core.BaseCrudAllController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 12:00
 */
@RestController
@RequestMapping("/api/v1/admin/project")
class ProjectController @Autowired protected constructor(projectService: ProjectService) :
    BaseCrudAllController<ProjectEditModel, ProjectItemModel>(projectService)