package net.ukisoft.ukistreams.startup

import net.ukisoft.ukistreams.model.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 21.08.2020 04:51
 */
@Service
@Transactional
class ApplicationStartup @Autowired constructor(userService: UserService) :
    ApplicationListener<ContextRefreshedEvent> {
    private val userService: UserService
    override fun onApplicationEvent(contextRefreshedEvent: ContextRefreshedEvent) {
        userService.createSuperAdmin()
    }

    init {
        this.userService = userService
    }
}