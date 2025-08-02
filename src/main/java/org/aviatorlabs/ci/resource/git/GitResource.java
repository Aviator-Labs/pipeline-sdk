package org.aviatorlabs.ci.resource.git;

import org.aviatorlabs.ci.resource.git.get.GitGet;
import org.aviatorlabs.ci.resource.git.put.GitPut;
import org.aviatorlabs.ci.sdk.resource.Resource;

public class GitResource extends Resource {
    protected GitResource(String name, GitResourceType type, GitResourceConfig config) {
        super(name, type, config);
    }

    public static GitResource create(String name, GitResourceConfig config) {
        GitResourceType type = GitResourceType.create();

        return new GitResource(name, type, config);
    }

    public static GitResource create(String name, GitResourceType type, GitResourceConfig config) {
        return new GitResource(name, type, config);
    }

    @Override
    public GitGet createGetDefinition() {
        return GitGet.create(this);
    }

    @Override
    public GitGet createGetDefinition(String identifier) {
        return GitGet.create(this, identifier);
    }

    @Override
    public GitPut createPutDefinition() {
        return GitPut.create(this);
    }

    @Override
    public GitPut createPutDefinition(String identifier) {
        return GitPut.create(this, identifier);
    }
}
