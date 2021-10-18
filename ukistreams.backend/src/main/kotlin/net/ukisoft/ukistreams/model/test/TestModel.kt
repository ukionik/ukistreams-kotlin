package net.ukisoft.ukistreams.model.test

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Started in IntelliJ IDEA
 * Author: Andrey Vyzhanov
 * Created: 18.10.2021 23:15
 **/
data class TestModel(
    val name: String,
    val duration: Duration,
    val duration2: Duration,
    val localDateTime: LocalDateTime,
    val localDate: LocalDate
) {
}