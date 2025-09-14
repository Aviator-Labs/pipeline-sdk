package org.aviatorlabs.ci.test.mock.get;

import org.aviatorlabs.ci.sdk.resource.get.Get;
import org.aviatorlabs.ci.test.mock.MockResource;

public class MockGet extends Get {

    private MockGet(MockResource resource, String name) {
        super(resource, name);
    }

    private MockGet(MockResource resource) {
        super(resource);
    }

    public static MockGet create(MockResource resource) {
        return new MockGet(resource);
    }

    public static MockGet create(MockResource resource, String name) {
        return new MockGet(resource, name);
    }
}
