package com.github.jlgrock.informatix.workmanager.domain
import javax.persistence.AttributeConverter
import javax.persistence.Converter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

/**
 *
 */
@Converter(autoApply = true)
class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {
    @Override
    Date convertToDatabaseColumn(LocalDateTime date) {
        if (date == null) {
            return null
        }
        Instant instant = date.atZone(ZoneId.systemDefault()).toInstant()
        Date.from(instant)
    }

    @Override
    LocalDateTime convertToEntityAttribute(Date date) {
        if (date == null) {
            return null
        }
        LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
    }
}
