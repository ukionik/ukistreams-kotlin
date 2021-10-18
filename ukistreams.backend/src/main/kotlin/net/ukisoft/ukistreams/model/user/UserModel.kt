package net.ukisoft.ukistreams.model.user


/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 05.05.2020 23:55
 */
data class UserModel(
    val id: Long,
    val login: String,
    val password: String,
    val dropmaniaAccess: Boolean,
    val admin: Boolean
)