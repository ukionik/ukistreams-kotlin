package net.ukisoft.ukistreams.model.game

import org.springframework.data.jpa.repository.JpaRepository
import net.ukisoft.ukistreams.entities.Game

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:19
 */
interface GameRepository : JpaRepository<Game, Long>