package net.ukisoft.ukistreams.model.project

import net.ukisoft.ukistreams.entities.Project
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Started in IntelliJ IDEA
 * Author: Andrey Vyzhanov
 * Created: 19.10.2021 16:49
 */
interface ProjectRepository : JpaRepository<Project, Long>