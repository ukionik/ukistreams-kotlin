package net.ukisoft.ukistreams.model.playthrough

import net.ukisoft.ukistreams.entities.Vod
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:28
 */
interface VodRepository : JpaRepository<Vod, Long>