package org.aviatorlabs.ci.resource.tracker;

import org.aviatorlabs.ci.resource.Bundled;
import org.aviatorlabs.ci.resource.registry.RegistryImageConfig;
import org.aviatorlabs.ci.resource.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;

@Deprecated
public class TrackerResourceType extends ResourceType<TrackerResourceType, TrackerConfig> {

    private TrackerResourceType(String name) {
        super(name);
    }

    private TrackerResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static TrackerResourceType create() {
        return new TrackerResourceType(Bundled.TRACKER.getTypeName());
    }

    public static TrackerResourceType create(RegistryImageConfig config) {
        return new TrackerResourceType(Bundled.TRACKER.getTypeName(), config);
    }

    @Override
    protected TrackerResourceType getSelf() {
        return this;
    }
}
