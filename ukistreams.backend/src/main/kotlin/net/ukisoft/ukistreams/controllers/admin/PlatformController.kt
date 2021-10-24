package net.ukisoft.ukistreams.controllers.admin

import net.ukisoft.ukistreams.model.core.BaseCrudAllController
import net.ukisoft.ukistreams.model.platform.PlatformEditModel
import net.ukisoft.ukistreams.model.platform.PlatformItemModel
import net.ukisoft.ukistreams.model.platform.PlatformService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 22.08.2020 03:12
 */
@RestController
@RequestMapping("/api/admin/platform")
class PlatformController @Autowired constructor(platformService: PlatformService) :
    BaseCrudAllController<PlatformEditModel, PlatformItemModel>(platformService)