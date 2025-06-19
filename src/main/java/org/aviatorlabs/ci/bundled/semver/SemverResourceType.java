package org.aviatorlabs.ci.bundled.semver;

import org.aviatorlabs.ci.bundled.Bundled;
import org.aviatorlabs.ci.bundled.registry.RegistryImageConfig;
import org.aviatorlabs.ci.bundled.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class SemverResourceType extends ResourceType<SemverResourceType, AbstractSemverDriverConfig<?>> {

    private SemverResourceType(String name) {
        super(name);
    }

    private SemverResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static SemverResourceType create() {
        return new SemverResourceType(Bundled.SEMVER.getTypeName());
    }

    public static SemverResourceType create(RegistryImageConfig config) {
        return new SemverResourceType(Bundled.SEMVER.getTypeName(), config);
    }

    @Override
    protected SemverResourceType getSelf() {
        return this;
    }
}
