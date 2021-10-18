package net.ukisoft.ukistreams.entities

import javax.persistence.Column
import javax.persistence.Entity

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 11:45
 */
@Entity
class Project : BaseEntity() {
    @Column(unique = true)
    var name: String? = null
}