package org.aviatorlabs.ci.bundled.bosh.release;

import org.aviatorlabs.ci.sdk.resource.Resource;

public class BoshReleaseResource extends Resource {
    protected BoshReleaseResource(String name, BoshReleaseResourceType type, BoshReleaseConfig config) {
        super(name, type, config);
    }

    public static BoshReleaseResource create(String name, BoshReleaseConfig config) {
        BoshReleaseResourceType type = BoshReleaseResourceType.create();

        return new BoshReleaseResource(name, type, config);
    }

    public static BoshReleaseResource create(String name, BoshReleaseResourceType type, BoshReleaseConfig config) {
        return new BoshReleaseResource(name, type, config);
    }
}
