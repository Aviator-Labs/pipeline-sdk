package org.aviatorlabs.ci.bundled.mock.get;

import org.aviatorlabs.ci.bundled.mock.MockResource;
import org.aviatorlabs.ci.sdk.resource.get.Get;
import org.aviatorlabs.ci.sdk.util.Validator;

public class MockGet extends Get {

    private MockGet(MockResource resource) {
        super(resource);
    }

    private MockGet(MockResource resource, String name) {
        super(resource, name);
    }

    public static MockGet create(MockResource resource, String name) {
        Validator.validateIdentifier(name);

        return new MockGet(resource, name);
    }

    public static MockGet create(MockResource resource) {
        return new MockGet(resource);
    }
}
