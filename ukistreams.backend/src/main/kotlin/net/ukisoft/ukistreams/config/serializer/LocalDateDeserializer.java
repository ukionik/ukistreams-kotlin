package net.ukisoft.ukistreams.config.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 29.08.2020 06:28
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
  @Override
  public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return LocalDate.parse(jsonParser.getValueAsString(), dateTimeFormatter);
  }
}
