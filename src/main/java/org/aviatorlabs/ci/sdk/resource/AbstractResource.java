package org.aviatorlabs.ci.sdk.resource;

import com.google.gson.annotations.SerializedName;
import org.aviatorlabs.ci.sdk.util.Validator;
import lombok.Getter;

@Getter
abstract class AbstractResource {

    private final String name;
    private final String type;
    @SerializedName("source")
    private final IResourceConfig config;

    protected AbstractResource(String name, String type, IResourceConfig config) {
        Validator.validateIdentifier(name);

        this.name = name;
        this.type = type;
        this.config = config;
    }
}
