package net.ukisoft.ukistreams.entities

import javax.persistence.*

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 12:45
 */
@Entity
class Game : BaseEntity() {
    var name: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var platform: Platform? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var genre: Genre? = null

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var review: GameReview? = null
        set(review) {
            if (review == null) {
                val thisReview = this.review
                thisReview?.game = null
            } else {
                review.game = this
            }
            field = review
        }
}