package org.aviatorlabs.ci.bundled.time;

import org.aviatorlabs.ci.bundled.Bundled;
import org.aviatorlabs.ci.bundled.registry.RegistryImageConfig;
import org.aviatorlabs.ci.bundled.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;
import org.aviatorlabs.ci.sdk.util.Validator;

public class TimeResourceType extends ResourceType<TimeResourceType, TimeConfig> {

    private TimeResourceType(String name) {
        super(name);
    }

    private TimeResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static TimeResourceType create() {
        return new TimeResourceType(Bundled.TIME.getTypeName());
    }

    public static TimeResourceType create(RegistryImageConfig config) {
        return new TimeResourceType(Bundled.TIME.getTypeName(), config);
    }

    public static TimeResourceType create(String name, RegistryImageConfig config) {
        Validator.validateIdentifier(name);

        return new TimeResourceType(name, config);
    }

    @Override
    protected TimeResourceType getSelf() {
        return this;
    }
}
