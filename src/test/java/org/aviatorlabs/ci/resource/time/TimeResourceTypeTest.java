package org.aviatorlabs.ci.resource.time;

import org.aviatorlabs.ci.resource.Bundled;
import org.aviatorlabs.ci.resource.registry.RegistryImageConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeResourceTypeTest {

    @Test
    void simpleResourceType() {
        // Arrange

        // Act
        TimeResourceType type = TimeResourceType.create();

        // Assert
        assertNotNull(type);
        assertEquals(Bundled.REGISTRY_IMAGE.getTypeName(), type.getType());
        assertEquals(Bundled.TIME.getTypeName(), type.getName());
    }

    @Test
    void overrideResourceTypeVersion() {
        // Arrange
        RegistryImageConfig config = RegistryImageConfig.create("concourse/time");

        // Act
        TimeResourceType type = TimeResourceType.create(config);

        // Assert
        assertNotNull(type);
        assertEquals(Bundled.REGISTRY_IMAGE.getTypeName(), type.getType());
        assertEquals(Bundled.TIME.getTypeName(), type.getName());
        assertInstanceOf(RegistryImageConfig.class, type.getConfig());

        RegistryImageConfig c = (RegistryImageConfig) type.getConfig();

        assertEquals("concourse/time", c.getRepository());
    }

    @Test
    void overrideResourceTypeVersionAndName() {
        // Arrange
        RegistryImageConfig config = RegistryImageConfig.create("concourse/time");

        // Act
        TimeResourceType type = TimeResourceType.create("new-time", config);

        // Assert
        assertNotNull(type);
        assertEquals(Bundled.REGISTRY_IMAGE.getTypeName(), type.getType());
        assertEquals("new-time", type.getName());
        assertInstanceOf(RegistryImageConfig.class, type.getConfig());

        RegistryImageConfig c = (RegistryImageConfig) type.getConfig();

        assertEquals("concourse/time", c.getRepository());
    }

    @Test
    void markTypePrivileged() {
        // Arrange
        TimeResourceType type = TimeResourceType.create();

        // Act
        type.markPrivileged();

        // Assert
        assertTrue(type.getPrivileged());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1ms", "1s", "1m", "1h", "1d", "1w", "1y", "1y1m100s"})
    void validCheckDelays(String duration) {
        // Arrange
        TimeResourceType type;

        // Act
        type = assertDoesNotThrow(() -> TimeResourceType.create().setCheckEvery(duration));

        // Assert
        assertEquals(duration, type.getCheckEvery());
    }

    @ParameterizedTest
    @ValueSource(strings = {"123_job", "1m1y", "1d1w"})
    void invalidCheckDelay(String duration) {
        // Arrange
        TimeResourceType type = TimeResourceType.create();

        assertThrows(IllegalArgumentException.class, () -> type.setCheckEvery(duration));
    }

    @Test
    void addWorkerTags() {
        // Arrange
        TimeResourceType type = TimeResourceType.create();

        // Act
        type.addTag("worker_aws").addTag("worker_azure");

        // Assert
        assertEquals(2, type.getTags().size());
        assertTrue(type.getTags().containsAll(List.of("worker_aws", "worker_azure")));
    }
}