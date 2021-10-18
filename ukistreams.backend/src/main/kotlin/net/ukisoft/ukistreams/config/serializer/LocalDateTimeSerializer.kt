package net.ukisoft.ukistreams.config.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.io.IOException
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 29.08.2020 06:12
 */
class LocalDateTimeSerializer : JsonSerializer<LocalDateTime>() {
    @Throws(IOException::class)
    override fun serialize(
        localDateTime: LocalDateTime,
        jsonGenerator: JsonGenerator,
        serializerProvider: SerializerProvider
    ) {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z")
        jsonGenerator.writeString(localDateTime.atZone(ZoneId.systemDefault()).format(dateTimeFormatter))
    }
}