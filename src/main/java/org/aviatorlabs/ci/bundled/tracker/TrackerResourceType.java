package org.aviatorlabs.ci.bundled.tracker;

import org.aviatorlabs.ci.bundled.Bundled;
import org.aviatorlabs.ci.bundled.registry.RegistryImageConfig;
import org.aviatorlabs.ci.bundled.registry.RegistryImageResourceType;
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
