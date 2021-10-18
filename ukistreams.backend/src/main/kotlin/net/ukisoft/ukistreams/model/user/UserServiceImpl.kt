package net.ukisoft.ukistreams.model.user

import net.ukisoft.ukistreams.entities.User
import net.ukisoft.ukistreams.model.common.AppSettingsRepository
import net.ukisoft.ukistreams.util.default
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 05.05.2020 23:58
 */
@Service
@Transactional
class UserServiceImpl @Autowired constructor(
    userRepository: UserRepository, appSettingsRepository: AppSettingsRepository
) : UserService {
    private val userRepository: UserRepository
    private val appSettingsRepository: AppSettingsRepository
    override fun findByLogin(login: String): UserModel? {
        val user: User = userRepository.findByLogin(login) ?: return null
        return UserModel(
            user.id!!,
            user.login!!,
            user.password!!,
            user.hasDropmaniaAccess.default(false),
            appSettingsRepository.superAdminLogin.equals(user.login, true)
        )
    }

    override fun createSuperAdmin() {
        createUserInDatabase(appSettingsRepository.superAdminLogin, appSettingsRepository.superAdminPassword)
    }

    override fun createUser(login: String, password: String, key: String) {
        if (appSettingsRepository.registrationKey != key) {
            throw RuntimeException("Invalid Key")
        }
        createUserInDatabase(login, password)
    }

    private fun createUserInDatabase(login: String, password: String) {
        var user: User? = userRepository.findByLogin(login)
        if (user == null) {
            val passwordEncoder = BCryptPasswordEncoder()
            user = User()
            user.login = login
            user.password = passwordEncoder.encode(password)
            user.hasDropmaniaAccess = true
            userRepository.save(user)
        }
    }

    init {
        this.userRepository = userRepository
        this.appSettingsRepository = appSettingsRepository
    }
}