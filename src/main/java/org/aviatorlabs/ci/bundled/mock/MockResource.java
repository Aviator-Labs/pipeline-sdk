package org.aviatorlabs.ci.bundled.mock;

import org.aviatorlabs.ci.bundled.mock.get.MockGet;
import org.aviatorlabs.ci.bundled.mock.put.MockPut;
import org.aviatorlabs.ci.sdk.resource.Resource;

public class MockResource extends Resource {
    protected MockResource(String name, MockResourceType type, MockConfig config) {
        super(name, type, config);
    }

    public static MockResource create(String name, MockConfig config) {
        MockResourceType type = MockResourceType.create();

        return new MockResource(name, type, config);
    }

    public static MockResource create(String name, MockResourceType type, MockConfig config) {
        return new MockResource(name, type, config);
    }

    @Override
    public MockGet createGetDefinition() {
        return MockGet.create(this);
    }

    @Override
    public MockGet createGetDefinition(String identifier) {
        return MockGet.create(this, identifier);
    }

    @Override
    public MockPut createPutDefinition() {
        return MockPut.create(this);
    }

    @Override
    public MockPut createPutDefinition(String identifier) {
        return MockPut.create(this, identifier);
    }
}
