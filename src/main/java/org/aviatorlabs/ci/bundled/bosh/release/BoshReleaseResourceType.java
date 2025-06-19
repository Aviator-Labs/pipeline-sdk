package org.aviatorlabs.ci.bundled.bosh.release;

import org.aviatorlabs.ci.bundled.Bundled;
import org.aviatorlabs.ci.bundled.registry.RegistryImageConfig;
import org.aviatorlabs.ci.bundled.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class BoshReleaseResourceType extends ResourceType<BoshReleaseResourceType, BoshReleaseConfig> {
    private BoshReleaseResourceType(String name) {
        super(name);
    }

    private BoshReleaseResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static BoshReleaseResourceType create() {
        return new BoshReleaseResourceType(Bundled.BOSH_IO_RELEASE.getTypeName());
    }

    public static BoshReleaseResourceType create(RegistryImageConfig config) {
        return new BoshReleaseResourceType(Bundled.BOSH_IO_RELEASE.getTypeName(), config);
    }

    @Override
    protected BoshReleaseResourceType getSelf() {
        return this;
    }
}
