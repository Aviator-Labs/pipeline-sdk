package org.aviatorlabs.ci.test.git.get;

import org.aviatorlabs.ci.sdk.resource.get.Get;
import org.aviatorlabs.ci.test.git.GitResource;

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
}
