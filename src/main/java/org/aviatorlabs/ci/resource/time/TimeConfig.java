package org.aviatorlabs.ci.resource.time;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.aviatorlabs.ci.sdk.resource.IResourceConfig;
import org.aviatorlabs.ci.sdk.util.Validator;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.*;

@Getter
public class TimeConfig implements IResourceConfig {
    private String interval;
    private String location;

    private String start;
    private String end;

    private List<String> days;

    @SerializedName("initial_version")
    private Boolean initialVersion;

    private TimeConfig() {
    }

    public static TimeConfig create() {
        return new TimeConfig();
    }

    public TimeConfig setInterval(String interval) {
        Validator.validateDuration(interval);
        this.interval = interval;

        return this;
    }

    public TimeConfig setLocation(String location) {
        if (!isValidTimezone(location)) {
            throw new RuntimeException("Not a valid timezone");
        }

        this.location = location;

        return this;
    }

    public TimeConfig setStartAndEnd(String start, String end) {
        if (start == null || start.trim().isEmpty()) {
            throw new IllegalArgumentException("Start cannot be null or empty");
        }

        if (end == null || end.trim().isEmpty()) {
            throw new IllegalArgumentException("End cannot be null or empty");
        }

        LocalTime startParse = parseFlexibleTime(start);
        LocalTime endParse = parseFlexibleTime(end);

        if (endParse.isBefore(startParse)) {
            throw new UnsupportedOperationException("End Time cannot be before Start Time");
        }

        this.start = start;
        this.end = end;

        return this;
    }

    public TimeConfig addDay(DayOfWeek dayOfWeek) {
        if (this.days == null) {
            this.days = new ArrayList<>();
        }

        this.days.add(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH));

        return this;
    }

    public TimeConfig emitInitialVersion() {
        this.initialVersion = true;

        return this;
    }

    private boolean isValidTimezone(String timezone) {
        return Set.of(TimeZone.getAvailableIDs()).contains(timezone);
    }

    private LocalTime parseFlexibleTime(String timeString) {
        // Define an array of potential formatters
        Set<DateTimeFormatter> formatters = Set.of(
                DateTimeFormatter.ofPattern("h:m a"),   // 3:04 PM
                DateTimeFormatter.ofPattern("h a"),     // 3 PM
                DateTimeFormatter.ofPattern("ha"),      // 3PM
                DateTimeFormatter.ofPattern("HH:m"),     // 15:04
                DateTimeFormatter.ofPattern("HHm")       // 1504
        );

        Set<DateTimeFormatter> deprecatedFormatters = Set.of(
                DateTimeFormatter.ofPattern("h:m a Z"), // "3:04 PM -0700"
                DateTimeFormatter.ofPattern("h a Z"),   // "3PM -0700"
                DateTimeFormatter.ofPattern("ha Z"),    // "3 PM -0700"
                DateTimeFormatter.ofPattern("HH:m Z"),   // "15:04 -0700"
                DateTimeFormatter.ofPattern("HHm Z")     // "1504 -0700"
        );

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalTime.parse(timeString, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter if parsing fails
            }
        }

        for (DateTimeFormatter formatter : deprecatedFormatters) {
            try {
                return LocalTime.parse(timeString, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next formatter if parsing fails
            }
        }
        throw new DateTimeParseException("Unable to parse time string: " + timeString, timeString, 0);
    }
}
