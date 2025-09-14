package org.aviatorlabs.ci.sdk.step.task.config;

import org.aviatorlabs.ci.sdk.resource.AnonymousResource;
import org.aviatorlabs.ci.test.registry.RegistryImageConfig;
import org.aviatorlabs.ci.test.registry.RegistryImageType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TaskConfigTest {

    @ParameterizedTest
    @EnumSource(value = Platform.class, names = {"DARWIN", "WINDOWS"})
    void platformIsNotLinux(Platform platform) {
        // Arrange
        RegistryImageType type = RegistryImageType.create();
        RegistryImageConfig resourceConfig = RegistryImageConfig.create("busybox");

        // Act
        TaskConfig config = TaskConfig.create(platform, AnonymousResource.create(type, resourceConfig), Command.createCommand("sh").addArg("hello"));

        // Assert
        assertNull(config.getResource());
    }

    @Test
    void platformIsLinux() {
        // Arrange
        RegistryImageType type = RegistryImageType.create();
        RegistryImageConfig resourceConfig = RegistryImageConfig.create("busybox");

        // Act
        TaskConfig config = TaskConfig.create(Platform.LINUX, AnonymousResource.create(type, resourceConfig), Command.createCommand("sh").addArg("hello"));

        // Assert
        assertNotNull(config.getResource());
    }
}