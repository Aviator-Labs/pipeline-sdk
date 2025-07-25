package org.aviatorlabs.ci.bundled.pool;

import org.aviatorlabs.ci.sdk.resource.Resource;

public class PoolResource extends Resource {
    protected PoolResource(String name, PoolResourceType type, PoolConfig config) {
        super(name, type, config);
    }

    public static PoolResource create(String name, PoolConfig config) {
        PoolResourceType type = PoolResourceType.create();

        return new PoolResource(name, type, config);
    }

    public static PoolResource create(String name, PoolResourceType type, PoolConfig config) {
        return new PoolResource(name, type, config);
    }
}
