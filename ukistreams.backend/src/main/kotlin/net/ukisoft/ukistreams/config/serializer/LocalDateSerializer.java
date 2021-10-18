package net.ukisoft.ukistreams.config.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 29.08.2020 06:28
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> {
  @Override
  public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    jsonGenerator.writeString(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).format(dateTimeFormatter));
  }
}
