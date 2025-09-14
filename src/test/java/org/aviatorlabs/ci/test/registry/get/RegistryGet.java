package org.aviatorlabs.ci.test.registry.get;


import org.aviatorlabs.ci.sdk.resource.get.Get;
import org.aviatorlabs.ci.test.registry.RegistryImageResource;

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
}
