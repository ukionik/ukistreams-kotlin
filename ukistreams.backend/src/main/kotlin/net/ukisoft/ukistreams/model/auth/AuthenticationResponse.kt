package net.ukisoft.ukistreams.model.auth

/**
 * Started in IntelliJ IDEA
 * Author: Andrey Vyzhanov
 * Created: 18.10.2021 23:25
 **/
data class AuthenticationResponse(
    val login: String,
    val token: String,
    val admin: Boolean,
    val dropmaniaAccess: Boolean
)