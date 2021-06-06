package com.atherys.core.db.converter;

import com.atherys.core.utils.TimeUtils;

import javax.persistence.AttributeConverter;
import java.time.Duration;

public class DurationConverter implements AttributeConverter<Duration, String> {
    @Override
    public String convertToDatabaseColumn(Duration attribute) {
        return TimeUtils.durationToString(attribute);
    }

    @Override
    public Duration convertToEntityAttribute(String dbData) {
        return TimeUtils.stringToDuration(dbData);
    }
}
