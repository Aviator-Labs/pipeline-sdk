package org.aviatorlabs.ci.bundled.docker;

import org.aviatorlabs.ci.sdk.resource.Resource;

public class DockerResource extends Resource {
    protected DockerResource(String name, DockerResourceType type, DockerConfig config) {
        super(name, type, config);
    }

    public static DockerResource create(String name, DockerConfig config) {
        DockerResourceType type = DockerResourceType.create();

        return new DockerResource(name, type, config);
    }

    public static DockerResource create(String name, DockerResourceType type, DockerConfig config) {
        return new DockerResource(name, type, config);
    }
}
