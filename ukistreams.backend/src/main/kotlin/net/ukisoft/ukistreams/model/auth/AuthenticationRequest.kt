package net.ukisoft.ukistreams.model.auth

/**
 * Started in IntelliJ IDEA
 * Author: Andrey Vyzhanov
 * Created: 18.10.2021 23:22
 **/
data class AuthenticationRequest(
    val login: String, val password: String
)