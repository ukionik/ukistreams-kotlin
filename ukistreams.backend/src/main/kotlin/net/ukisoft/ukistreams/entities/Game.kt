package net.ukisoft.ukistreams.entities

import javax.persistence.*

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 12:45
 */
@Entity
class Game : BaseEntity() {
    var name: String = ""

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var platform: Platform

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var genre: Genre

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    lateinit var review: GameReview
}