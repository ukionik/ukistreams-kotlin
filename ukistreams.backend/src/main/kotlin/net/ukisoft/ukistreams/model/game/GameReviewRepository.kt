package net.ukisoft.ukistreams.model.game

import net.ukisoft.ukistreams.entities.GameReview
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:20
 */
interface GameReviewRepository : JpaRepository<GameReview, Long>