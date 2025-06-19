package org.aviatorlabs.ci.bundled.docker;

import org.aviatorlabs.ci.bundled.Bundled;
import org.aviatorlabs.ci.bundled.registry.RegistryImageConfig;
import org.aviatorlabs.ci.bundled.registry.RegistryImageResourceType;
import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class DockerResourceType extends ResourceType<DockerResourceType, DockerConfig> {

    private DockerResourceType(String name) {
        super(name);
    }

    private DockerResourceType(String name, RegistryImageConfig config) {
        super(name, RegistryImageResourceType.create(), config);
    }

    public static DockerResourceType create() {
        return new DockerResourceType(Bundled.DOCKER_IMAGE.getTypeName());
    }

    public static DockerResourceType create(RegistryImageConfig config) {
        return new DockerResourceType(Bundled.DOCKER_IMAGE.getTypeName(), config);
    }

    @Override
    protected DockerResourceType getSelf() {
        return this;
    }
}
