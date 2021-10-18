package net.ukisoft.ukistreams.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 05.05.2020 23:53
 */
@Entity
@Table(name = "user_")
class User : BaseEntity() {
    @Column(unique = true)
    var login: String? = null

    var password: String? = null

    var hasDropmaniaAccess: Boolean? = null
}