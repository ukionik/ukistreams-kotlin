package net.ukisoft.ukistreams.security

import net.ukisoft.ukistreams.model.UserModel

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.05.2020 00:58
 */
object JwtUserFactory {
    fun create(user: UserModel): JwtUser {
        var role: String? = null
        if (user.isAdmin()) {
            role = "ADMIN"
        }
        return JwtUser(user.getLogin(), user.getPassword(), role)
    }
}