package org.aviatorlabs.ci.bundled.semver.put;

import org.aviatorlabs.ci.bundled.semver.SemverResource;
import org.aviatorlabs.ci.sdk.resource.put.Put;
import org.aviatorlabs.ci.sdk.util.Validator;

public class SemverPut extends Put {
    private SemverPut(SemverResource resource) {
        super(resource);
    }

    private SemverPut(SemverResource resource, String name) {
        super(resource, name);
    }

    public static SemverPut create(SemverResource semverResource) {
        return new SemverPut(semverResource);
    }

    public static SemverPut create(SemverResource semverResource, String identifier) {
        Validator.validateIdentifier(identifier);

        return new SemverPut(semverResource, identifier);
    }
}
