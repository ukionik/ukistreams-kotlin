package net.ukisoft.ukistreams.model.user

import net.ukisoft.ukistreams.entities.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 05.05.2020 23:52
 */
interface UserRepository : JpaRepository<User, Long> {
    fun findByLogin(login: String): User?
}