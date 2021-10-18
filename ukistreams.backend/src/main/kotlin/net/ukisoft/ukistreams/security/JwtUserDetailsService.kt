package net.ukisoft.ukistreams.security

import net.ukisoft.ukistreams.service.admin.UserService
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
        val user: Unit = userService.findByLogin(login) ?: throw UsernameNotFoundException("login: $login not found")
        return JwtUserFactory.create(user)
    }

    init {
        this.userService = userService
    }
}