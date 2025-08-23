package org.aviatorlabs.ci.sdk.resource;

import com.google.gson.annotations.SerializedName;
import org.aviatorlabs.ci.sdk.step.task.config.TaskConfig;
import lombok.Getter;

/**
 * An Anonymous Resource Type is used for specifying container images for running tasks
 * through the {@link TaskConfig}
 */
@Getter
public class AnonymousResource<T extends IResourceConfig> {
    @SerializedName("type")
    private final String resourceType;

    @SerializedName("source")
    private final T config;

    /**
     * Creates an Anonymous Resource given the Resource Type and corresponding Resource Configuration
     *
     * @param type   Resource Type. Note: If not a bundled resource type, then the type needs to be added to the Pipeline
     *               Resource Types array.
     * @param config Configuration for the Resource Type
     */
    protected AnonymousResource(ResourceType type, T config) {
        this.resourceType = type.getName();
        this.config = config;
    }

    /**
     * Creates an Anonymous Resource given the Resource Type and corresponding Resource Configuration
     *
     * @param type   Resource Type. Note: If not a bundled resource type, then the type needs to be added to the Pipeline
     *               Resource Types array.
     * @param config Configuration for the Resource Type
     * @return An Anonymous Resource of specified type and config
     */
    public static <T extends IResourceConfig> AnonymousResource<T> create(ResourceType type, T config) {
        return new AnonymousResource<>(type, config);
    }
}
