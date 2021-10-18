package net.ukisoft.ukistreams.entity

import javax.persistence.Column
import javax.persistence.Entity

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 22.08.2020 01:27
 */
@Entity
class Platform : BaseEntity() {
    var ordinal: Int? = null
    var name: String? = null

    @Column(unique = true)
    var shortName: String? = null
}