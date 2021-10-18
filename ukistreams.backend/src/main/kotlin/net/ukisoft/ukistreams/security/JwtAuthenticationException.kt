package net.ukisoft.ukistreams.security

import org.springframework.security.core.AuthenticationException

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.05.2020 02:00
 */
class JwtAuthenticationException(explanation: String?) : AuthenticationException(explanation) {
}