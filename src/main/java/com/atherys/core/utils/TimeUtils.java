package com.atherys.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.Duration;

public class TimeUtils {
    public static String durationToString(Duration duration) {
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
            result = (days == 0 ? "" : days + "d") +
                    (hours == 0 ? "" : hours + "h") +
                    (minutes == 0 ? "" : minutes + "m") +
                    (seconds == 0 ? "" : seconds + "s") +
                    (seconds == 0 ? "" : millis + "M");
        }
        value.setValue(result);
    }

    public static Duration stringToDuration(String durationString) {
        if (StringUtils.isEmpty(durationString)) {
            return null;
        }

        String delimiter = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";
        String[] tokens = durationString.split(delimiter);
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
}
