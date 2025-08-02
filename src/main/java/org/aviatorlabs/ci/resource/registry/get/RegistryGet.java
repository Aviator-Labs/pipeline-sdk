package org.aviatorlabs.ci.resource.registry.get;

import org.aviatorlabs.ci.resource.registry.RegistryImageResource;
import org.aviatorlabs.ci.resource.registry.RegistryVersion;
import org.aviatorlabs.ci.sdk.resource.get.Get;

public class RegistryGet extends Get {

    private RegistryGet(RegistryImageResource resource) {
        super(resource);
    }

    private RegistryGet(RegistryImageResource resource, String name) {
        super(resource, name);
    }

    public static RegistryGet create(RegistryImageResource resource) {
        return new RegistryGet(resource);
    }

    public static RegistryGet create(RegistryImageResource resource, String name) {
        return new RegistryGet(resource, name);
    }

    public Get setVersion(RegistryVersion version) {
        return super.setVersion(version);
    }
}
