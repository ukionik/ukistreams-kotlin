package net.ukisoft.ukistreams.controllers.admin

import net.ukisoft.ukistreams.core.BaseCrudAllController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 29.08.2020 20:12
 */
@RestController
@RequestMapping("/api/admin/playthrough")
class PlaythroughController @Autowired protected constructor(playthroughService: PlaythroughService) :
    BaseCrudAllController<PlaythroughEditModel?, PlaythroughItemModel?>(playthroughService)