package org.aviatorlabs.ci.resource.docker;

import org.aviatorlabs.ci.resource.Bundled;
import org.aviatorlabs.ci.resource.registry.RegistryImageConfig;
import org.aviatorlabs.ci.resource.registry.RegistryImageResourceType;
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
