package net.ukisoft.ukistreams.model.auth

/**
 * Started in IntelliJ IDEA
 * Author: Andrey Vyzhanov
 * Created: 18.10.2021 23:28
 **/
data class RegistrationRequest(
    val login: String,
    val password: String,
    val key: String
)
