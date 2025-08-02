package org.aviatorlabs.ci.resource.pool;

import org.aviatorlabs.ci.resource.Bundled;
import org.aviatorlabs.ci.resource.registry.RegistryImageConfig;
import org.aviatorlabs.ci.resource.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class PoolResourceType extends ResourceType<PoolResourceType, PoolConfig> {

    private PoolResourceType(String name) {
        super(name);
    }

    private PoolResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static PoolResourceType create() {
        return new PoolResourceType(Bundled.POOL.getTypeName());
    }

    public static PoolResourceType create(RegistryImageConfig config) {
        return new PoolResourceType(Bundled.POOL.getTypeName(), config);
    }

    @Override
    protected PoolResourceType getSelf() {
        return this;
    }
}
