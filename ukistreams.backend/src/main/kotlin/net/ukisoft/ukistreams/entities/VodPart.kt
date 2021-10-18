package net.ukisoft.ukistreams.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 21.09.2020 22:38
 */
@Entity
class VodPart : BaseEntity() {
    var ordinal: Int? = null

    @Column(length = 1000)
    var url: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var vod: Vod? = null
}