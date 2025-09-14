package org.aviatorlabs.ci.test.mock;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.aviatorlabs.ci.sdk.resource.IResourceConfig;

@Getter
public class MockConfig implements IResourceConfig {

    @SerializedName("mirror_self")
    private Boolean mirrorSelf;

    protected MockConfig() {
    }

    public static MockConfig create() {
        return new MockConfig();
    }

    public MockConfig mirrorSelf() {
        this.mirrorSelf = true;

        return this;
    }
}