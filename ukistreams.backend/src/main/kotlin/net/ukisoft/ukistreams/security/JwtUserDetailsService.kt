package net.ukisoft.ukistreams.security

import net.ukisoft.ukistreams.model.user.UserModel
import net.ukisoft.ukistreams.model.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.05.2020 00:54
 */
@Service("jwtUserDetailsService")
class JwtUserDetailsService @Autowired constructor(userService: UserService) : UserDetailsService {
    private val userService: UserService
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(login: String): UserDetails {
        val user: UserModel = userService.findByLogin(login) ?: throw UsernameNotFoundException("login: $login not found")
        return JwtUserFactory.create(user)
    }

    init {
        this.userService = userService
    }
}