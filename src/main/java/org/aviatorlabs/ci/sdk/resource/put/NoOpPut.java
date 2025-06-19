package org.aviatorlabs.ci.sdk.resource.put;

import org.aviatorlabs.ci.sdk.resource.Resource;

public class NoOpPut extends Put {
    public NoOpPut(Resource resource) {
        super(resource);
    }

    public NoOpPut(Resource resource, String name) {
        super(resource, name);
    }
}
