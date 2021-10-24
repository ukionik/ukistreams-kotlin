package net.ukisoft.ukistreams.model.genre

import net.ukisoft.ukistreams.core.CrudAllServiceImpl
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 07:27
 */
@Service
@Transactional
class GenreServiceImpl @Autowired constructor(genreRepository: GenreRepository?) :
    CrudAllServiceImpl<Genre?, GenreEditModel?, GenreItemModel?>(
        genreRepository,
        GenreEditModelMapper(),
        GenreItemModelMapper()
    ), GenreService {
    protected fun defaultSort(): Sort {
        return Sort.by(Sort.Direction.ASC, "ordinal")
    }
}