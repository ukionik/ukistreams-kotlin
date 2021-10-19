package net.ukisoft.ukistreams.model.genre

import org.springframework.data.jpa.repository.JpaRepository
import net.ukisoft.ukistreams.entities.Genre

/**
 * Started in IntelliJ IDEA
 * Author: Andrey Vyzhanov
 * Created: 19.10.2021 16:48
 */
interface GenreRepository : JpaRepository<Genre, Long>