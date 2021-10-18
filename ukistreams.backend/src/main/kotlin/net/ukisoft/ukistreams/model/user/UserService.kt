package net.ukisoft.ukistreams.model.user

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 05.05.2020 23:54
 */
interface UserService {
    fun findByLogin(login: String): UserModel?
    fun createSuperAdmin()
    fun createUser(login: String, password: String, key: String)
}