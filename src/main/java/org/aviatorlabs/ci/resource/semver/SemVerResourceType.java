package org.aviatorlabs.ci.resource.semver;

import org.aviatorlabs.ci.resource.Bundled;
import org.aviatorlabs.ci.resource.registry.RegistryImageConfig;
import org.aviatorlabs.ci.resource.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class SemVerResourceType extends ResourceType<SemVerResourceType, SemVerConfig> {

    private SemVerResourceType(String name) {
        super(name);
    }

    private SemVerResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static SemVerResourceType create() {
        return new SemVerResourceType(Bundled.SEMVER.getTypeName());
    }

    public static SemVerResourceType create(RegistryImageConfig config) {
        return new SemVerResourceType(Bundled.SEMVER.getTypeName(), config);
    }

    @Override
    protected SemVerResourceType getSelf() {
        return this;
    }
}
