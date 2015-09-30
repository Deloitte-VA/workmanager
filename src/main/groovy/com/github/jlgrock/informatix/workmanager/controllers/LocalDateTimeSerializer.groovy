package com.github.jlgrock.informatix.workmanager.controllers
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

import java.time.LocalDateTime
import java.time.ZoneId
/**
 *
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
        if (arg0 == null) {
            arg1.writeNull()
        }
        arg1.writeNumber(arg0.atZone(ZoneId.systemDefault()).toEpochSecond())
    }
}
