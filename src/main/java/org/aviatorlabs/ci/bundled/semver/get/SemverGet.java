package org.aviatorlabs.ci.bundled.semver.get;

import org.aviatorlabs.ci.bundled.semver.SemverResource;
import org.aviatorlabs.ci.sdk.resource.get.Get;
import org.aviatorlabs.ci.sdk.util.Validator;

public class SemverGet extends Get {
    private SemverGet(SemverResource resource) {
        super(resource);
    }

    private SemverGet(SemverResource resource, String name) {
        super(resource, name);
    }

    public static SemverGet create(SemverResource semverResource) {
        return new SemverGet(semverResource);
    }

    public static SemverGet create(SemverResource semverResource, String identifier) {
        Validator.validateIdentifier(identifier);

        return new SemverGet(semverResource, identifier);
    }
}
