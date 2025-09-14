package org.aviatorlabs.ci.test.git;

import lombok.Getter;
import org.aviatorlabs.ci.sdk.resource.IResourceConfig;

import java.net.URI;

@Getter
public class GitConfig implements IResourceConfig {
    private final URI uri;

    private final String branch;

    protected GitConfig(URI uri, String branch) {
        this.uri = uri;
        this.branch = branch;
    }

    public static GitConfig create(String uri) {
        return create(uri, null);
    }

    public static GitConfig create(String uri, String branch) {
        return new GitConfig(URI.create(uri), branch);
    }
}