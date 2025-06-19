package org.aviatorlabs.ci.sdk.varsource;

import org.aviatorlabs.ci.sdk.ISerializableEnum;
import lombok.Getter;

@Getter
public enum VarSourceType implements ISerializableEnum {

    VAULT("vault"),
    SSM("ssm"),
    DUMMY("dummy");

    private final String displayName;

    VarSourceType(String displayName) {
        this.displayName = displayName;
    }
}
