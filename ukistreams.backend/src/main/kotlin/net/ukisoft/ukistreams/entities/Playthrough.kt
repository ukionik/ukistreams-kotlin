package net.ukisoft.ukistreams.entities

import net.ukisoft.ukistreams.enums.Blind
import net.ukisoft.ukistreams.enums.FirstPlaythrough
import net.ukisoft.ukistreams.enums.Region
import java.time.Duration
import java.time.LocalDateTime
import javax.persistence.*

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 28.08.2020 12:46
 */
@Entity
class Playthrough : BaseEntity() {
    @ManyToOne(fetch = FetchType.LAZY)
    var game: Game? = null

    @Enumerated(EnumType.STRING)
    var region: Region? = null
    var duration: Duration? = null
    var startDate: LocalDateTime? = null
    var endDate: LocalDateTime? = null
    var pickedBy: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var project: Project? = null

    @Enumerated(EnumType.STRING)
    var firstPlaythrough: FirstPlaythrough? = null

    @Enumerated(EnumType.STRING)
    var blind: Blind? = null
    var comment: String? = null

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    private var vods: MutableSet<Vod> = HashSet()
    fun addVod(vod: Vod) {
        vod.playthrough = this
        vods.add(vod)
    }

    fun removeVod(vod: Vod) {
        vod.playthrough = null
        vods.remove(vod)
    }

    fun getVods(): Set<Vod> {
        return vods
    }

    fun setVods(vods: MutableSet<Vod>) {
        this.vods = vods
    }
}