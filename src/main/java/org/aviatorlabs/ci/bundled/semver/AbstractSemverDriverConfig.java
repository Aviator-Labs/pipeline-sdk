package org.aviatorlabs.ci.bundled.semver;

import com.google.gson.annotations.SerializedName;
import org.aviatorlabs.ci.sdk.resource.IResourceConfig;
import org.aviatorlabs.ci.sdk.util.Validator;

public abstract class AbstractSemverDriverConfig<T> implements IResourceConfig {

    @SerializedName("initial_version")
    private String initialVersion;

    private SemverDriver driver;

    AbstractSemverDriverConfig(SemverDriver driver) {
        this.driver = driver;
    }

    public T setInitialVersion(String initialVersion) {
        Validator.validateSemver(initialVersion);

        this.initialVersion = initialVersion;

        return getSelf();
    }

    /**
     * @return <T> The instance of self
     */
    protected abstract T getSelf();
}
