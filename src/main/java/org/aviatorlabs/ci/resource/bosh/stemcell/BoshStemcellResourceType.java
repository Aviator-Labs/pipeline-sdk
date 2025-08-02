package org.aviatorlabs.ci.resource.bosh.stemcell;

import org.aviatorlabs.ci.resource.Bundled;
import org.aviatorlabs.ci.resource.registry.RegistryImageConfig;
import org.aviatorlabs.ci.resource.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class BoshStemcellResourceType extends ResourceType<BoshStemcellResourceType, BoshStemcellConfig> {

    private BoshStemcellResourceType(String name) {
        super(name);
    }

    public BoshStemcellResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static BoshStemcellResourceType create() {
        return new BoshStemcellResourceType(Bundled.BOSH_IO_STEMCELL.getTypeName());
    }

    public static BoshStemcellResourceType create(RegistryImageConfig config) {
        return new BoshStemcellResourceType(Bundled.BOSH_IO_STEMCELL.getTypeName(), config);
    }

    @Override
    protected BoshStemcellResourceType getSelf() {
        return this;
    }
}
