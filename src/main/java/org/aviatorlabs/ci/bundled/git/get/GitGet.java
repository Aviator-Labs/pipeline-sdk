package org.aviatorlabs.ci.bundled.git.get;

import org.aviatorlabs.ci.bundled.git.GitResource;
import org.aviatorlabs.ci.bundled.git.GitVersion;
import org.aviatorlabs.ci.sdk.resource.get.Get;

public class GitGet extends Get {

    private GitGet(GitResource resource, String name) {
        super(resource, name);
    }

    private GitGet(GitResource resource) {
        super(resource);
    }

    public static GitGet create(GitResource resource) {
        return new GitGet(resource);
    }

    public static GitGet create(GitResource resource, String name) {
        return new GitGet(resource, name);
    }

    public Get setVersion(GitVersion version) {
        return super.setVersion(version);
    }
}
