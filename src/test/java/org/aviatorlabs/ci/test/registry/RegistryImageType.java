package org.aviatorlabs.ci.test.registry;

import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class RegistryImageType extends ResourceType<RegistryImageType, RegistryImageConfig> {
    private RegistryImageType(String name) {
        super(name);
    }

    public static RegistryImageType create() {
        return new RegistryImageType("registry-image");
    }

    public static RegistryImageType create(String newName) {
        return new RegistryImageType(newName);
    }

    @Override
    protected RegistryImageType getSelf() {
        return this;
    }
}
