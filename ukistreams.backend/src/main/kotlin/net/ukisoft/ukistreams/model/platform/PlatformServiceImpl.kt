package net.ukisoft.ukistreams.model.platform

import net.ukisoft.ukistreams.entities.Platform
import net.ukisoft.ukistreams.model.core.CrudAllServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 22.08.2020 02:09
 */
@Service
@Transactional
class PlatformServiceImpl @Autowired constructor(platformRepository: PlatformRepository) :
    CrudAllServiceImpl<Platform, PlatformEditModel, PlatformItemModel>(
        platformRepository,
        PlatformEditModelMapper(),
        PlatformItemModelMapper()
    ), PlatformService {
    override fun defaultSort(): Sort? {
        return Sort.by(Sort.Direction.ASC, "ordinal")
    }
}