package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.entities.VodPart
import net.ukisoft.ukistreams.model.core.CustomRepositoryImpl
import net.ukisoft.ukistreams.model.core.RepositoryFilter

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 04.12.2020 12:07
 */
class VodPartRepositoryImpl : CustomRepositoryImpl<VodPart, RepositoryFilter>(), VodPartRepositoryCustom