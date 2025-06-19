package org.aviatorlabs.ci.sdk.step.task.config;

import org.aviatorlabs.ci.sdk.ISerializableEnum;
import lombok.Getter;

@Getter
public enum Platform implements ISerializableEnum {

    LINUX("linux"),
    DARWIN("darwin"),
    WINDOWS("windows");

    private final String displayName;

    Platform(String displayName) {
        this.displayName = displayName;
    }
}
