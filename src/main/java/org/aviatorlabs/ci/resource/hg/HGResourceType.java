package org.aviatorlabs.ci.resource.hg;

import org.aviatorlabs.ci.resource.Bundled;
import org.aviatorlabs.ci.resource.registry.RegistryImageConfig;
import org.aviatorlabs.ci.resource.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class HGResourceType extends ResourceType<HGResourceType, HGConfig> {

    private HGResourceType(String name) {
        super(name);
    }

    private HGResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static HGResourceType create() {
        return new HGResourceType(Bundled.HG.getTypeName());
    }

    public static HGResourceType create(RegistryImageConfig config) {
        return new HGResourceType(Bundled.HG.getTypeName(), config);
    }

    @Override
    protected HGResourceType getSelf() {
        return this;
    }
}
