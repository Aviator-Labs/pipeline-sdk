package org.aviatorlabs.ci.resource.git;

import org.aviatorlabs.ci.resource.Bundled;
import org.aviatorlabs.ci.resource.registry.RegistryImageConfig;
import org.aviatorlabs.ci.resource.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class GitResourceType extends ResourceType<GitResourceType, GitResourceConfig> {

    private GitResourceType(String name) {
        super(name);
    }

    private GitResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static GitResourceType create() {
        return new GitResourceType(Bundled.GIT.getTypeName());
    }

    public static GitResourceType create(RegistryImageConfig config) {
        return new GitResourceType(Bundled.GIT.getTypeName(), config);
    }

    @Override
    protected GitResourceType getSelf() {
        return this;
    }
}
