package net.ukisoft.ukistreams.entity

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToOne

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 12:57
 */
@Entity
class GameReview : BaseEntity() {
    @OneToOne(fetch = FetchType.LAZY)
    var game: Game? = null
    var rate: Double? = null

    var difficulty: Double? = null
}