package net.ukisoft.ukistreams.controllers.admin

import net.ukisoft.ukistreams.core.BaseCrudAllController

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 12:00
 */
@RestController
@RequestMapping("/api/v1/admin/project")
class ProjectController @Autowired protected constructor(projectService: ProjectService?) :
    BaseCrudAllController<ProjectEditModel?, ProjectItemModel?>(projectService)