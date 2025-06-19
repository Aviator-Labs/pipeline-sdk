package org.aviatorlabs.ci.bundled.mock;

import org.aviatorlabs.ci.bundled.Bundled;
import org.aviatorlabs.ci.bundled.registry.RegistryImageConfig;
import org.aviatorlabs.ci.bundled.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class MockResourceType extends ResourceType<MockResourceType, MockConfig> {

    private MockResourceType(String name) {
        super(name);
    }

    private MockResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static MockResourceType create() {
        return new MockResourceType(Bundled.MOCK.getTypeName());
    }

    public static MockResourceType create(RegistryImageConfig config) {
        return new MockResourceType(Bundled.MOCK.getTypeName(), config);
    }

    @Override
    protected MockResourceType getSelf() {
        return this;
    }
}
