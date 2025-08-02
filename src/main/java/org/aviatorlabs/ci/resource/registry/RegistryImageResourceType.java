package org.aviatorlabs.ci.resource.registry;

import org.aviatorlabs.ci.resource.Bundled;
import org.aviatorlabs.ci.sdk.resource.ResourceType;
import org.aviatorlabs.ci.sdk.util.Validator;

public class RegistryImageResourceType extends ResourceType<RegistryImageResourceType, RegistryImageConfig> {
    private RegistryImageResourceType(String name) {
        super(name);
    }

    private RegistryImageResourceType(String name, RegistryImageConfig source) {
        super(name, RegistryImageResourceType.create(), source);
    }

    public static RegistryImageResourceType create() {
        return new RegistryImageResourceType(Bundled.REGISTRY_IMAGE.getTypeName());
    }

    public static RegistryImageResourceType create(RegistryImageConfig source) {
        return new RegistryImageResourceType(Bundled.REGISTRY_IMAGE.getTypeName(), source);
    }

    public static RegistryImageResourceType create(String name, RegistryImageConfig config) {
        Validator.validateIdentifier(name);

        return new RegistryImageResourceType(name, config);
    }

    @Override
    protected RegistryImageResourceType getSelf() {
        return this;
    }
}
