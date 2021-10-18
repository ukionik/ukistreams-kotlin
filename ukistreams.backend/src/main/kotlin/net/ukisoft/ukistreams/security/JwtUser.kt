package net.ukisoft.ukistreams.security

import net.ukisoft.ukistreams.security.JwtTokenProvider
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import net.ukisoft.ukistreams.security.JwtTokenFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.GenericFilterBean
import kotlin.Throws
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.userdetails.UserDetailsService
import net.ukisoft.ukistreams.security.JwtAuthenticationException
import java.lang.IllegalArgumentException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import net.ukisoft.ukistreams.security.JwtUserFactory
import net.ukisoft.ukistreams.security.JwtUser
import java.util.ArrayList

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.05.2020 00:57
 */
class JwtUser(private val login: String, private val password: String, role: String?) : UserDetails {
    private var authorities: Collection<GrantedAuthority>? = null

    ///============================== Getters / Setters ==============================
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities!!
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return login
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    } ///============================== End of Getters / Setters ==============================

    init {
        authorities = if (role != null) {
            setOf(SimpleGrantedAuthority("ADMIN"))
        } else {
            ArrayList()
        }
    }
}