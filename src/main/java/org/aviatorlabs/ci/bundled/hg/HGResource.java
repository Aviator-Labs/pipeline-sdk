package org.aviatorlabs.ci.bundled.hg;

import org.aviatorlabs.ci.sdk.resource.Resource;

public class HGResource extends Resource {
    protected HGResource(String name, HGResourceType type, HGConfig config) {
        super(name, type, config);
    }

    public static HGResource create(String name, HGConfig config) {
        HGResourceType type = HGResourceType.create();

        return new HGResource(name, type, config);
    }

    public static HGResource create(String name, HGResourceType type, HGConfig config) {
        return new HGResource(name, type, config);
    }
}
