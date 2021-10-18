package net.ukisoft.ukistreams.entity

import net.ukisoft.ukistreams.enums.VodType
import javax.persistence.*

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 13:26
 */
@Entity
class Vod : BaseEntity() {
    @Enumerated(EnumType.STRING)
    var type: VodType? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var playthrough: Playthrough? = null

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    private var parts: MutableSet<VodPart> = HashSet()
    fun addPart(part: VodPart) {
        part.vod = this
        parts.add(part)
    }

    fun removePart(part: VodPart) {
        part.vod = null
        parts.remove(part)
    }

    fun getParts(): Set<VodPart> {
        return parts
    }

    fun setParts(parts: MutableSet<VodPart>) {
        this.parts = parts
    }
}