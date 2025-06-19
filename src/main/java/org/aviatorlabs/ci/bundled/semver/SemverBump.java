package org.aviatorlabs.ci.bundled.semver;

import org.aviatorlabs.ci.sdk.ISerializableEnum;
import lombok.Getter;

@Getter
public enum SemverBump implements ISerializableEnum {
    MAJOR("major"),
    MINOR("minor"),
    PATCH("patch"),
    FINAL("final");

    private final String displayName;

    SemverBump(String displayName) {
        this.displayName = displayName;
    }
}
