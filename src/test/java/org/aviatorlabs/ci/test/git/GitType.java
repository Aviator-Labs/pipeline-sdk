package org.aviatorlabs.ci.test.git;

import org.aviatorlabs.ci.sdk.resource.ResourceType;

public class GitType extends ResourceType<GitType, GitConfig> {
    private GitType(String name) {
        super(name);
    }

    public static GitType create() {
        return new GitType("git");
    }

    public static GitType create(String newName) {
        return new GitType(newName);
    }

    @Override
    protected GitType getSelf() {
        return this;
    }
}
