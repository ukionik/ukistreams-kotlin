package net.ukisoft.ukistreams.model.common

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 22.08.2020 03:14
 */
@Service
class AppSettingsRepositoryImpl : AppSettingsRepository {
    @Value("\${app.superadmin.login}")
    override val superAdminLogin: String = ""

    @Value("\${app.superadmin.password}")
    override val superAdminPassword: String = ""

    @Value("\${app.registration.key}")
    override val registrationKey: String = ""
}