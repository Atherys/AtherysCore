package com.atherys.core.serialize;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.StringTokenizer;

public class DurationTypeSerializer implements TypeSerializer<Duration> {
    @Override
    public Duration deserialize(TypeToken<?> type, ConfigurationNode value) throws ObjectMappingException {
        String val = value.getString();

        if (StringUtils.isEmpty(val)) {
            throw new ObjectMappingException("Cannot parse Duration: Is either null or empty string.");
        }

        StringTokenizer tokenizer = new StringTokenizer(val);

        Duration result = Duration.ZERO;

        while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            long amount = Long.parseLong(token.substring(0, token.length() - 1));
            char lastChar = token.charAt(token.length() - 1);

            switch (lastChar) {
                case 'S':
                    result = result.plusMillis(amount);
                    break;
                case 's':
                    result = result.plusSeconds(amount);
                    break;
                case 'm':
                    result = result.plusMinutes(amount);
                    break;
                case 'h':
                    result = result.plusHours(amount);
                    break;
                case 'd':
                    result = result.plusDays(amount);
                    break;
            }
        }

        return result;
    }

    @Override
    public void serialize(TypeToken<?> type, Duration obj, ConfigurationNode value) throws ObjectMappingException {
        value.setValue(obj.toString());
    }
}
