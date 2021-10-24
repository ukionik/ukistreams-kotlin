package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.entities.VodPart
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 22.09.2020 02:48
 */
interface VodPartRepository : JpaRepository<VodPart, Long>, VodPartRepositoryCustom