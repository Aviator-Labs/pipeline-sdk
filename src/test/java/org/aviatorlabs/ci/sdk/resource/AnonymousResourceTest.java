package org.aviatorlabs.ci.sdk.resource;

import org.aviatorlabs.ci.bundled.mock.MockResourceType;
import org.aviatorlabs.ci.bundled.registry.RegistryImageConfig;
import org.aviatorlabs.ci.bundled.registry.RegistryImageResourceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AnonymousResourceTest {

    @Test
    void basicCreate() {
        // Arrange

        // Act
        AnonymousResource<RegistryImageConfig> resource = AnonymousResource.create("busybox");

        // Assert
        assertEquals(RegistryImageResourceType.create().getName(), resource.getResourceType());
        assertEquals("busybox", resource.getConfig().getRepository());
        assertNull(resource.getConfig().getTag());
    }

    @Test
    void repositoryAndTag() {
        // Arrange

        // Act
        AnonymousResource<RegistryImageConfig> resource = AnonymousResource.create("busybox", "v1.0.0");

        // Assert
        assertEquals(RegistryImageResourceType.create().getName(), resource.getResourceType());
        assertEquals("busybox", resource.getConfig().getRepository());
        assertEquals("v1.0.0", resource.getConfig().getTag());
    }

    @Test
    void registryConfig() {
        // Arrange
        RegistryImageConfig config = RegistryImageConfig.create("openjdk", "17");

        // Act
        AnonymousResource<RegistryImageConfig> resource = AnonymousResource.create(config);

        // Assert
        assertEquals(RegistryImageResourceType.create().getName(), resource.getResourceType());
        assertEquals("openjdk", resource.getConfig().getRepository());
        assertEquals("17", resource.getConfig().getTag());
    }

    @Test
    void fullConfig() {
        // Arrange
        RegistryImageConfig config = RegistryImageConfig.create("openjdk", "17");

        // Act
        AnonymousResource<RegistryImageConfig> resource = AnonymousResource.create(MockResourceType.create(), config);

        // Assert
        assertEquals(MockResourceType.create().getName(), resource.getResourceType());
        assertEquals("openjdk", resource.getConfig().getRepository());
        assertEquals("17", resource.getConfig().getTag());
    }
}