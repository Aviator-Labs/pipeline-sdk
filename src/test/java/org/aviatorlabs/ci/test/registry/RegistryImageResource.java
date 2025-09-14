package org.aviatorlabs.ci.test.registry;

import org.aviatorlabs.ci.sdk.resource.Resource;
import org.aviatorlabs.ci.test.registry.get.RegistryGet;

public class RegistryImageResource extends Resource {
    protected RegistryImageResource(String name, RegistryImageType type, RegistryImageConfig config) {
        super(name, type, config);
    }

    public static RegistryImageResource create(String name, RegistryImageConfig config) {
        RegistryImageType type = RegistryImageType.create();

        return new RegistryImageResource(name, type, config);
    }

    public static RegistryImageResource create(String name, RegistryImageType type, RegistryImageConfig config) {
        return new RegistryImageResource(name, type, config);
    }

    @Override
    public RegistryGet createGetDefinition() {
        return RegistryGet.create(this);
    }
}
