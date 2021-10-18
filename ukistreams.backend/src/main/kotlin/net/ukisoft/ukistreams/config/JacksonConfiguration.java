package net.ukisoft.ukistreams.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.ukisoft.ukistreams.config.serializer.LocalDateDeserializer;
import net.ukisoft.ukistreams.config.serializer.LocalDateSerializer;
import net.ukisoft.ukistreams.config.serializer.LocalDateTimeDeserializer;
import net.ukisoft.ukistreams.config.serializer.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 29.08.2020 06:08
 */
@Configuration
public class JacksonConfiguration {
  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
    mapper.registerModule(javaTimeModule);
    return mapper;
  }
}
