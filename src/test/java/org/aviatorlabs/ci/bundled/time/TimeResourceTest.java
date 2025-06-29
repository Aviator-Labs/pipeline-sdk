package org.aviatorlabs.ci.bundled.time;

import org.aviatorlabs.ci.bundled.Bundled;
import org.aviatorlabs.ci.bundled.registry.RegistryImageConfig;
import org.aviatorlabs.ci.sdk.resource.get.Get;
import org.aviatorlabs.ci.sdk.resource.put.Put;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

class TimeResourceTest {

    @Test
    void createTimeResource() {
        // Arrange
        TimeConfig config = TimeConfig.create().addDay(DayOfWeek.MONDAY);

        // Act
        TimeResource resource = TimeResource.create("time", config);

        // Assert
        assertNotNull(resource);
        assertEquals("time", resource.getName());
    }

    @Test
    void changeResourceType() {
        // Arrange
        TimeConfig config = TimeConfig.create().addDay(DayOfWeek.MONDAY);
        RegistryImageConfig registryImageConfig = RegistryImageConfig.create("concourse/time", "1.0.0");
        TimeResourceType type = TimeResourceType.create(registryImageConfig);

        // Act
        TimeResource resource = TimeResource.create("time", type, config);

        // Assert
        assertNotNull(resource);
        assertEquals("time", resource.getName());
        assertEquals(Bundled.TIME.getTypeName(), resource.getType());
        assertInstanceOf(RegistryImageConfig.class, type.getConfig());

        RegistryImageConfig returned = (RegistryImageConfig) type.getConfig();

        assertEquals("concourse/time", returned.getRepository());
    }

    @Test
    void createSparseGet() {
        // Arrange
        TimeConfig config = TimeConfig.create().addDay(DayOfWeek.MONDAY);
        TimeResource resource = TimeResource.create("time", config);

        // Act
        Get get = resource.createGetDefinition();

        // Assert
        assertNotNull(get);
        assertEquals("time", get.getIdentifier());
    }

    @Test
    void changeSparseGetIdentifier() {
        // Arrange
        TimeConfig config = TimeConfig.create().addDay(DayOfWeek.MONDAY);
        TimeResource resource = TimeResource.create("time", config);

        // Act
        Get get = resource.createGetDefinition("identifier");

        // Assert
        assertNotNull(get);
        assertEquals("identifier", get.getIdentifier());
        assertEquals("time", get.getResource());
    }

    @ParameterizedTest
    @ValueSource(strings = {"((my_var))-get", "MY_GET"})
    void setInvalidGetIdentifier(String identifier) {
        // Arrange
        TimeConfig config = TimeConfig.create().addDay(DayOfWeek.MONDAY);
        TimeResource resource = TimeResource.create("time", config);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> resource.createGetDefinition(identifier));
    }

    @Test
    void createSparsePut() {
        // Arrange
        TimeConfig config = TimeConfig.create().addDay(DayOfWeek.MONDAY);
        TimeResource resource = TimeResource.create("time", config);

        // Act
        Put put = resource.createPutDefinition();

        // Assert
        assertNotNull(put);
        assertEquals("time", put.getIdentifier());
    }

    @Test
    void changeSparsePutIdentifier() {
        // Arrange
        TimeConfig config = TimeConfig.create().addDay(DayOfWeek.MONDAY);
        TimeResource resource = TimeResource.create("time", config);

        // Act
        Put put = resource.createPutDefinition("identifier");

        // Assert
        assertNotNull(put);
        assertEquals("identifier", put.getIdentifier());
        assertEquals("time", put.getResource());
    }

    @ParameterizedTest
    @ValueSource(strings = {"((my_var))-put", "MY_PUT"})
    void setInvalidPutIdentifier(String identifier) {
        // Arrange
        TimeConfig config = TimeConfig.create().addDay(DayOfWeek.MONDAY);
        TimeResource resource = TimeResource.create("time", config);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> resource.createPutDefinition(identifier));
    }
}