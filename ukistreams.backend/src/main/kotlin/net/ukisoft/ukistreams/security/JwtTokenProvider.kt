package net.ukisoft.ukistreams.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import net.ukisoft.ukistreams.util.default
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.ArrayList
import javax.servlet.http.HttpServletRequest

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.05.2020 00:26
 */
@Component
class JwtTokenProvider @Autowired constructor(@Qualifier("jwtUserDetailsService") userDetailsService: UserDetailsService) {
    private val userDetailsService: UserDetailsService

    @Value("\${jwt.token.secret}")
    private val secret: String? = null
    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken: String = request.getHeader("Authorization").default("")
        return if (bearerToken.startsWith("Bearer")) {
            bearerToken.substring(7)
        } else null
    }

    fun validateToken(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            throw JwtAuthenticationException("JWT token is expired or invalid")
        } catch (e: IllegalArgumentException) {
            throw JwtAuthenticationException("JWT token is expired or invalid")
        }
    }

    fun createToken(login: String): String {
        val claims: Claims = Jwts.claims().setSubject(login)
        claims["roles"] = getRoleNames(login)
        return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    ///============================== Getters / Setters ==============================
    private fun getRoleNames(login: String): List<String> {
        val roles = ArrayList<String>()
        if (login.equals("admin", ignoreCase = true)) {
            roles.add("ADMIN")
        }
        return roles
    }

    fun getAuthentication(token: String): Authentication {
        val username = getUsername(token)
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getUsername(token: String): String {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.subject
    } ///============================== End of Getters / Setters ==============================

    init {
        this.userDetailsService = userDetailsService
    }
}