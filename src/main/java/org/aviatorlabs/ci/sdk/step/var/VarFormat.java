package org.aviatorlabs.ci.sdk.step.var;

import org.aviatorlabs.ci.sdk.ISerializableEnum;
import lombok.Getter;

@Getter
public enum VarFormat implements ISerializableEnum {
    JSON("json"),
    YAML("yaml"),
    YML("yml"),
    TRIM("trim"),
    RAW("raw");

    private final String displayName;

    VarFormat(String displayName) {
        this.displayName = displayName;
    }
}
