package net.ukisoft.ukistreams

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Profile

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 22.10.2020 01:50
 */
@SpringBootApplication
@Profile("prod")
class SpringBootTomcatApplication : SpringBootServletInitializer()