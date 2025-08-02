package org.aviatorlabs.ci.resource.githubrelease;

import org.aviatorlabs.ci.resource.Bundled;
import org.aviatorlabs.ci.resource.registry.RegistryImageConfig;
import org.aviatorlabs.ci.resource.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class GithubReleaseResourceType extends ResourceType<GithubReleaseResourceType, GithubReleaseConfig> {
    private GithubReleaseResourceType(String name) {
        super(name);
    }

    public GithubReleaseResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static GithubReleaseResourceType create() {
        return new GithubReleaseResourceType(Bundled.GITHUB_RELEASE.getTypeName());
    }

    public static GithubReleaseResourceType create(RegistryImageConfig config) {
        return new GithubReleaseResourceType(Bundled.GITHUB_RELEASE.getTypeName(), config);
    }

    @Override
    protected GithubReleaseResourceType getSelf() {
        return this;
    }
}
