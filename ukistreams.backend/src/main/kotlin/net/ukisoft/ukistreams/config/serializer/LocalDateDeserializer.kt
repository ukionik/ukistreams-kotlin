package net.ukisoft.ukistreams.config.serializer

import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.LocalDate
import kotlin.Throws
import java.io.IOException
import java.time.format.DateTimeFormatter
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.ZoneId
import java.time.LocalDateTime
import java.time.Instant

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 29.08.2020 06:28
 */
class LocalDateDeserializer : JsonDeserializer<LocalDate>() {
    @Throws(IOException::class)
    override fun deserialize(jsonParser: JsonParser, deserializationContext: DeserializationContext): LocalDate {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(jsonParser.valueAsString, dateTimeFormatter)
    }
}