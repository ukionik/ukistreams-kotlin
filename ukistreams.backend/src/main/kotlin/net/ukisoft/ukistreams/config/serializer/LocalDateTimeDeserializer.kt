package net.ukisoft.ukistreams.config.serializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.io.IOException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 29.08.2020 06:13
 */
class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime>() {
    @Throws(IOException::class)
    override fun deserialize(jsonParser: JsonParser, deserializationContext: DeserializationContext): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(jsonParser.valueAsString.toLong()), ZoneId.systemDefault())
    }
}