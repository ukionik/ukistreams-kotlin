package net.ukisoft.ukistreams.controllers

import net.ukisoft.ukistreams.model.auth.AuthenticationRequest
import net.ukisoft.ukistreams.model.auth.AuthenticationResponse
import net.ukisoft.ukistreams.model.auth.RegistrationRequest
import net.ukisoft.ukistreams.model.common.AppSettingsRepository
import net.ukisoft.ukistreams.model.user.UserModel
import net.ukisoft.ukistreams.model.user.UserService
import net.ukisoft.ukistreams.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.05.2020 03:33
 */
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
class AuthController @Autowired constructor(
    authenticationManager: AuthenticationManager,
    userService: UserService,
    jwtTokenProvider: JwtTokenProvider,
    appSettingsRepository: AppSettingsRepository
) {
    private val authenticationManager: AuthenticationManager
    private val userService: UserService
    private val jwtTokenProvider: JwtTokenProvider
    private val appSettingsRepository: AppSettingsRepository

    @PutMapping("login")
    fun login(@RequestBody request: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        val login: String = request.login
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(login, request.password))
        val user: UserModel = userService.findByLogin(login)
            ?: throw UsernameNotFoundException("User with login: $login not found")
        val token: String = jwtTokenProvider.createToken(login)
        val isAdmin: Boolean = appSettingsRepository.superAdminLogin.equals(login, true)
        return ResponseEntity.ok(
            AuthenticationResponse(
                login,
                token,
                isAdmin,
                user.dropmaniaAccess
            )
        )
    }

    @PostMapping("register")
    fun register(@RequestBody request: RegistrationRequest): ResponseEntity<AuthenticationResponse> {
        userService.createUser(request.login, request.password, request.key)
        val login: String = request.login
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(login, request.password))
        val user: UserModel = userService.findByLogin(login)
            ?: throw UsernameNotFoundException("User with login: $login not found")
        val token: String = jwtTokenProvider.createToken(login)
        val admin: Boolean = appSettingsRepository.superAdminLogin.equals(login, true)
        return ResponseEntity.ok(
            AuthenticationResponse(
                login,
                token,
                admin,
                user.dropmaniaAccess
            )
        )
    }

    init {
        this.authenticationManager = authenticationManager
        this.userService = userService
        this.jwtTokenProvider = jwtTokenProvider
        this.appSettingsRepository = appSettingsRepository
    }
}