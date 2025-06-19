package org.aviatorlabs.ci.bundled.mock.put;

import org.aviatorlabs.ci.bundled.mock.MockResource;
import org.aviatorlabs.ci.sdk.resource.put.Put;
import org.aviatorlabs.ci.sdk.util.Validator;

public class MockPut extends Put {
    private MockPut(MockResource resource) {
        super(resource);
    }

    private MockPut(MockResource resource, String name) {
        super(resource, name);
    }

    public static MockPut create(MockResource resource) {
        return new MockPut(resource);
    }

    public static MockPut create(MockResource resource, String name) {
        Validator.validateIdentifier(name);

        return new MockPut(resource, name);
    }
}
