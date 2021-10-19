package net.ukisoft.ukistreams.model.config

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 22.08.2020 03:14
 */
interface AppSettingsRepository {
    val superAdminLogin: String
    val superAdminPassword: String
    val registrationKey: String
}