package org.aviatorlabs.ci.test.mock;

import org.aviatorlabs.ci.sdk.resource.Resource;
import org.aviatorlabs.ci.test.mock.get.MockGet;

public class MockResource extends Resource {
    protected MockResource(String name, MockType type, MockConfig config) {
        super(name, type, config);
    }

    public static MockResource create(String name, MockConfig config) {
        MockType type = MockType.create();

        return new MockResource(name, type, config);
    }

    public static MockResource create(String name, MockType type, MockConfig config) {
        return new MockResource(name, type, config);
    }

    @Override
    public MockGet createGetDefinition() {
        return MockGet.create(this);
    }
}
