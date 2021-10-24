package net.ukisoft.ukistreams.controllers.admin

import net.ukisoft.ukistreams.model.core.BaseCrudAllController
import net.ukisoft.ukistreams.model.genre.GenreEditModel
import net.ukisoft.ukistreams.model.genre.GenreItemModel
import net.ukisoft.ukistreams.model.genre.GenreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 07:29
 */
@RestController
@RequestMapping("/api/admin/genre")
class GenreController @Autowired constructor(genreService: GenreService) :
    BaseCrudAllController<GenreEditModel, GenreItemModel>(genreService)