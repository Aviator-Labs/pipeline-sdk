package org.aviatorlabs.ci.bundled.time;

import org.aviatorlabs.ci.sdk.resource.Resource;
import org.aviatorlabs.ci.sdk.resource.get.Get;
import org.aviatorlabs.ci.sdk.resource.put.Put;

public class TimeResource extends Resource {
    protected TimeResource(String name, TimeResourceType type, TimeConfig config) {
        super(name, type, config);
    }

    public static TimeResource create(String name, TimeConfig config) {
        TimeResourceType type = TimeResourceType.create();

        return new TimeResource(name, type, config);
    }

    public static TimeResource create(String name, TimeResourceType type, TimeConfig config) {
        return new TimeResource(name, type, config);
    }

    @Override
    public Get createGetDefinition() {
        return new TimeGet(this);
    }

    @Override
    public Get createGetDefinition(String identifier) {
        return new TimeGet(this, identifier);
    }

    @Override
    public Put createPutDefinition() {
        return new TimePut(this);
    }

    @Override
    public Put createPutDefinition(String identifier) {
        return new TimePut(this, identifier);
    }

    /**
     * Sparse implementation of Get for TimeResource
     */
    static class TimeGet extends Get {
        TimeGet(TimeResource resource) {
            super(resource);
        }

        TimeGet(TimeResource resource, String name) {
            super(resource, name);
        }
    }

    /**
     * Sparse implementation of Put for TimeResource
     */
    static class TimePut extends Put {
        TimePut(TimeResource resource) {
            super(resource);
        }

        TimePut(TimeResource resource, String name) {
            super(resource, name);
        }
    }
}
