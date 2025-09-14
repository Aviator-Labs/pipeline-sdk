package org.aviatorlabs.ci.test.git;

import org.aviatorlabs.ci.sdk.resource.Resource;
import org.aviatorlabs.ci.test.git.get.GitGet;

public class GitResource extends Resource {
    protected GitResource(String name, GitType type, GitConfig config) {
        super(name, type, config);
    }

    public static GitResource create(String name, GitConfig config) {
        GitType type = GitType.create();

        return new GitResource(name, type, config);
    }

    public static GitResource create(String name, GitType type, GitConfig config) {
        return new GitResource(name, type, config);
    }

    @Override
    public GitGet createGetDefinition() {
        return GitGet.create(this);
    }
}
