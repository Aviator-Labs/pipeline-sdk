package org.aviatorlabs.ci.test.registry;

import lombok.Getter;
import org.aviatorlabs.ci.sdk.resource.IResourceConfig;

@Getter
public class RegistryImageConfig implements IResourceConfig {
    private final String repository;

    private final String tag;

    private RegistryImageConfig(String repository, String tag) {
        this.repository = repository;
        this.tag = tag;
    }

    public static RegistryImageConfig create(String repository) {
        return new RegistryImageConfig(repository, null);
    }

    public static RegistryImageConfig create(String repository, String tag) {
        return new RegistryImageConfig(repository, tag);
    }
}