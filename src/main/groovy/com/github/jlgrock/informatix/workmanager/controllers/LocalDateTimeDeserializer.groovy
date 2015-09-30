package com.github.jlgrock.informatix.workmanager.controllers

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

import java.time.Instant
import java.time.LocalDateTime

/**
 *
 */
class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
        if (arg0 == null) {
            return null
        }
        LocalDateTime.from(Instant.ofEpochSecond(arg0.getLongValue()))
    }
}

