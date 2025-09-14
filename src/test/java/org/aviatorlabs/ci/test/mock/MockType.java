package org.aviatorlabs.ci.test.mock;

import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class MockType extends ResourceType<MockType, MockConfig> {
    private MockType(String name) {
        super(name);
    }

    public static MockType create() {
        return new MockType("mock");
    }

    public static MockType create(String newName) {
        return new MockType(newName);
    }

    @Override
    protected MockType getSelf() {
        return this;
    }
}
