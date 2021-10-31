package net.ukisoft.ukistreams.entities

import javax.persistence.Column
import javax.persistence.Entity

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 27.08.2020 07:20
 */
@Entity
class Genre : BaseEntity() {
    var ordinal: Int = 0

    @Column(unique = true)
    var name: String = ""
}