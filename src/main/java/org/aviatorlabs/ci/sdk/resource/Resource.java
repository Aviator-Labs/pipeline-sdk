package org.aviatorlabs.ci.sdk.resource;

import com.google.gson.annotations.SerializedName;
import org.aviatorlabs.ci.sdk.resource.get.Get;
import org.aviatorlabs.ci.sdk.resource.put.Put;
import org.aviatorlabs.ci.sdk.util.Validator;
import lombok.Getter;

/**
 * Resources are the heart and soul of Concourse. They represent all external inputs to and outputs of jobs in the
 * pipeline.
 * <p>
 * Each resource represents a versioned artifact with an external source of truth. Configuring the same resource
 * in any pipeline on any Concourse cluster will behave the exact same way. Concourse will continuously check each
 * configured resource to discover new versions. These versions then flow through the pipeline via get steps
 * configured on Jobs.
 */
@Getter
public abstract class Resource extends AbstractResource {

    private String icon = null;

    @SerializedName("check_every")
    private String checkEvery = null;

    public Resource(String name, ResourceType<?, ?> type, IResourceConfig config) {
        super(name, type.getName(), config);
    }

    public Get createGetDefinition() {
        throw new UnsupportedOperationException("Get is not implemented");
    }

    public Get createGetDefinition(String identifier) {
        throw new UnsupportedOperationException("Get is not implemented");
    }

    public Put createPutDefinition() {
        throw new UnsupportedOperationException("Put is not implemented");
    }

    public Put createPutDefinition(String identifier) {
        throw new UnsupportedOperationException("Put is not implemented");
    }

    public Resource setIcon(String icon) {
        this.icon = icon;

        return this;
    }

    public Resource setCheckEvery(String duration) {
        if (duration.equals("never")) {
            this.checkEvery = duration;

            return this;
        }

        Validator.validateDuration(duration);
        this.checkEvery = duration;

        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        return ((Resource) obj).getName().equals(this.getName());
    }
}
