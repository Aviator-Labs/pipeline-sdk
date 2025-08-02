package org.aviatorlabs.ci.resource.git;

import org.aviatorlabs.ci.sdk.resource.IVersion;

public class GitVersion implements IVersion {
    private final String ref;

    private GitVersion(String ref) {
        this.ref = ref;
    }

    public static GitVersion create(String ref) {
        return new GitVersion(ref);
    }
}
