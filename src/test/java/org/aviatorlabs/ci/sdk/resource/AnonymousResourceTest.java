package org.aviatorlabs.ci.sdk.resource;


import org.aviatorlabs.ci.test.registry.RegistryImageConfig;
import org.aviatorlabs.ci.test.registry.RegistryImageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnonymousResourceTest {

    @Test
    void createAnonymousResource() {
        // Arrange
        RegistryImageType type = RegistryImageType.create();
        RegistryImageConfig resourceConfig = RegistryImageConfig.create("openjdk", "17");

        // Act
        AnonymousResource<RegistryImageConfig> resource = AnonymousResource.create(type, resourceConfig);

        // Assert
        assertEquals(RegistryImageType.create().getName(), resource.getResourceType());
        assertEquals("openjdk", resource.getConfig().getRepository());
        assertEquals("17", resource.getConfig().getTag());
    }
}