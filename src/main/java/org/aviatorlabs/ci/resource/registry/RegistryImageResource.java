package org.aviatorlabs.ci.resource.registry;

import org.aviatorlabs.ci.resource.registry.get.RegistryGet;
import org.aviatorlabs.ci.resource.registry.put.RegistryPut;
import org.aviatorlabs.ci.sdk.resource.Resource;

public class RegistryImageResource extends Resource {
    protected RegistryImageResource(String name, RegistryImageResourceType type, RegistryImageConfig config) {
        super(name, type, config);
    }

    public static RegistryImageResource create(String name, RegistryImageConfig config) {
        RegistryImageResourceType type = RegistryImageResourceType.create();

        return new RegistryImageResource(name, type, config);
    }

    public static RegistryImageResource create(String name, RegistryImageResourceType type, RegistryImageConfig config) {
        return new RegistryImageResource(name, type, config);
    }

    @Override
    public RegistryGet createGetDefinition() {
        return RegistryGet.create(this);
    }

    @Override
    public RegistryGet createGetDefinition(String identifier) {
        return RegistryGet.create(this, identifier);
    }

    @Override
    public RegistryPut createPutDefinition() {
        return RegistryPut.create(this);
    }

    @Override
    public RegistryPut createPutDefinition(String identifier) {
        return RegistryPut.create(this, identifier);
    }
}
