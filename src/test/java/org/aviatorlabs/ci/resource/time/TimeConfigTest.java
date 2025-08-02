package org.aviatorlabs.ci.resource.time;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TimeConfigTest {

    @ParameterizedTest
    @ValueSource(strings = {"1ms", "1s", "1m", "1h", "1d", "1w", "1y", "1y1m100s"})
    void validInterval(String duration) {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Act
        config.setInterval(duration);

        // Assert
        assertEquals(duration, config.getInterval());
    }

    @ParameterizedTest
    @ValueSource(strings = {"123_job", "1m1y", "1d1w"})
    void invalidInterval(String duration) {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> config.setInterval(duration));
    }

    @ParameterizedTest
    @ValueSource(strings = {"America/Sao_Paulo", "America/New_York"})
    void validLocation(String location) {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Act
        config.setLocation(location);

        // Assert
        assertEquals(location, config.getLocation());
    }

    @ParameterizedTest
    @ValueSource(strings = {"America/Miami", "Australia/Place"})
    void invalidLocation(String location) {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Assert
        assertThrows(RuntimeException.class, () -> config.setLocation(location));
    }

    @Test
    void addDay() {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Act
        config.addDay(DayOfWeek.MONDAY);

        // Assert
        assertTrue(config.getDays().contains("Monday"));
    }

    @Test
    void addMultipleDays() {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Act
        config.addDay(DayOfWeek.MONDAY).addDay(DayOfWeek.FRIDAY);

        // Assert
        assertTrue(config.getDays().containsAll(List.of("Friday", "Monday")));
    }

    @Test
    void emitInitial() {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Act
        config.emitInitialVersion();

        // Assert
        assertTrue(config.getInitialVersion());
    }

    @Test
    void specifyStartButNotEnd() {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> config.setStartAndEnd("1PM", null));
    }

    @Test
    void specifyEndButNotStart() {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> config.setStartAndEnd(null, "1PM"));
    }

    @Test
    void specifyStartEmptyString() {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> config.setStartAndEnd("", "3PM"));
    }

    @Test
    void specifyEndEmptyString() {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> config.setStartAndEnd("3PM", ""));
    }

    @ParameterizedTest
    @MethodSource(value = "validTimes")
    void validTimes(String start, String end) {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Act
        assertDoesNotThrow(() -> config.setStartAndEnd(start, end));

        // Assert
        assertEquals(start, config.getStart());
        assertEquals(end, config.getEnd());
    }

    @Test
    void invalidTime() {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Assert
        assertThrows(DateTimeParseException.class, () -> config.setStartAndEnd("3PM", "50:00"));
    }

    @Test
    void endAfterStart() {
        // Arrange
        TimeConfig config = TimeConfig.create();

        // Assert
        assertThrows(UnsupportedOperationException.class, () -> config.setStartAndEnd("3PM", "1400"));
    }

    // Private Method Sources
    private static Set<Arguments> validTimes() {
        return Set.of(
                Arguments.arguments("3:04 PM", "4:04 PM"),
                Arguments.arguments("3 PM", "4 PM"),
                Arguments.arguments("3PM", "4PM"),
                Arguments.arguments("15:04", "16:04"),
                Arguments.arguments("1504", "1604"),
                Arguments.arguments("3:04 PM -0700", "4:04 PM -0700"),
                Arguments.arguments("3 PM -0700", "4 PM -0700"),
                Arguments.arguments("3PM -0700", "4PM -0700"),
                Arguments.arguments("15:04 -0700", "16:04 -0700"),
                Arguments.arguments("1504 -0700", "1604 -0700")
        );
    }
}