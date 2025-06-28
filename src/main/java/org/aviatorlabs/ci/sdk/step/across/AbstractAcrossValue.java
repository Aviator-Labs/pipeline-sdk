package org.aviatorlabs.ci.sdk.step.across;

import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.SerializedName;
import org.aviatorlabs.ci.sdk.util.Validator;

public class AbstractAcrossValue {

    @SerializedName("var")
    private final String name;

    @SerializedName("max_in_flight")
    private JsonPrimitive maxInFlight;

    protected AbstractAcrossValue(String name) {
        Validator.validateIdentifier(name);

        this.name = name;
    }

    public String getVariable() {
        return String.format("((.:%s))", this.name);
    }

    public void setMaxInFlight(Integer maxInFlight) {
        if (maxInFlight < 1) {
            throw new IllegalArgumentException("Max in Flight cannot be less than 1");
        }

        this.maxInFlight = new JsonPrimitive(maxInFlight);
    }

    public void runAll() {
        this.maxInFlight = new JsonPrimitive("all");
    }
}
