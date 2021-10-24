package net.ukisoft.ukistreams.model.playthrough

import org.springframework.data.jpa.repository.JpaRepository
import net.ukisoft.ukistreams.entities.Playthrough
import net.ukisoft.ukistreams.model.playthrough.PlaythroughRepositoryCustom
import net.ukisoft.ukistreams.model.core.CustomRepository
import net.ukisoft.ukistreams.model.core.RepositoryFilter
import net.ukisoft.ukistreams.model.core.CustomRepositoryImpl
import net.ukisoft.ukistreams.model.playthrough.VodPartRepositoryCustom
import net.ukisoft.ukistreams.entities.VodPart

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 01.12.2020 16:53
 */
class PlaythroughRepositoryImpl : CustomRepositoryImpl<Playthrough, RepositoryFilter>(), PlaythroughRepositoryCustom