package org.aviatorlabs.ci.bundled.semver;

import org.aviatorlabs.ci.sdk.resource.Resource;

public class SemVerResource extends Resource {
    protected SemVerResource(String name, SemVerResourceType type, SemVerConfig config) {
        super(name, type, config);
    }

    public static SemVerResource create(String name, SemVerConfig config) {
        SemVerResourceType type = SemVerResourceType.create();

        return new SemVerResource(name, type, config);
    }

    public static SemVerResource create(String name, SemVerResourceType type, SemVerConfig config) {
        return new SemVerResource(name, type, config);
    }
}
