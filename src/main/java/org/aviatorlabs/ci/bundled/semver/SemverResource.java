package org.aviatorlabs.ci.bundled.semver;

import org.aviatorlabs.ci.bundled.semver.get.SemverGet;
import org.aviatorlabs.ci.bundled.semver.put.SemverPut;
import org.aviatorlabs.ci.sdk.resource.Resource;

public class SemverResource extends Resource {
    protected SemverResource(String name, SemverResourceType type, AbstractSemverDriverConfig<?> config) {
        super(name, type, config);
    }

    public static SemverResource create(String name, AbstractSemverDriverConfig<?> config) {
        SemverResourceType type = SemverResourceType.create();

        return new SemverResource(name, type, config);
    }

    public static SemverResource create(String name, SemverResourceType type, AbstractSemverDriverConfig<?> config) {
        return new SemverResource(name, type, config);
    }

    @Override
    public SemverGet createGetDefinition() {
        return SemverGet.create(this);
    }

    @Override
    public SemverGet createGetDefinition(String identifier) {
        return SemverGet.create(this, identifier);
    }

    @Override
    public SemverPut createPutDefinition() {
        return SemverPut.create(this);
    }

    @Override
    public SemverPut createPutDefinition(String identifier) {
        return SemverPut.create(this, identifier);
    }
}