package org.aviatorlabs.ci.bundled.githubrelease;

import org.aviatorlabs.ci.bundled.Bundled;
import org.aviatorlabs.ci.bundled.registry.RegistryImageConfig;
import org.aviatorlabs.ci.bundled.registry.RegistryImageResourceType;
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
