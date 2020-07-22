package com.atherys.core.serialize;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;

public class DurationTypeSerializer implements TypeSerializer<Duration> {
    @Override
    public Duration deserialize(TypeToken<?> type, ConfigurationNode value) throws ObjectMappingException {
        String val = value.getString();

        if (StringUtils.isEmpty(val)) {
            throw new ObjectMappingException("Cannot parse Duration: Is either null or empty string.");
        }

        String delimiter = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";
        String[] tokens = val.split(delimiter);
        Duration result = Duration.ZERO;

        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i - 1].matches("\\d+")) {
                long amount = Long.parseLong(tokens[i - 1]);
                switch (tokens[i]) {
                    case "S":
                        result = result.plusMillis(amount);
                        break;
                    case "s":
                        result = result.plusSeconds(amount);
                        break;
                    case "m":
                        result = result.plusMinutes(amount);
                        break;
                    case "h":
                        result = result.plusHours(amount);
                        break;
                    case "d":
                        result = result.plusDays(amount);
                        break;
                }
            }

        }

        return result;
    }

    @Override
    public void serialize(TypeToken<?> type, Duration obj, ConfigurationNode value) throws ObjectMappingException {
        String result = "";
        if (obj != null) {
            long days = obj.toDays();
            obj = obj.minusDays(days);
            long hours = obj.toHours();
            obj = obj.minusHours(hours);
            long minutes = obj.toMinutes();
            obj = obj.minusMinutes(minutes);
            long seconds = obj.getSeconds();
            obj = obj.minusSeconds(seconds);
            long millis = obj.toMillis();
            result = (days == 0 ? "" : days + " d") +
                    (hours == 0 ? "" : hours + " h") +
                    (minutes == 0 ? "" : minutes + "m") +
                    (seconds == 0 ? "" : seconds + "s") +
                    (seconds == 0 ? "" : millis + "M");
        }
        value.setValue(result);
    }
}
