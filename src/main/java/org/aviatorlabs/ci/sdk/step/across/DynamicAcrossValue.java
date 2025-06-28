package org.aviatorlabs.ci.sdk.step.across;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.util.HashSet;
import java.util.Set;

public class DynamicAcrossValue extends AbstractAcrossValue {
    private final Set<JsonElement> values = new HashSet<>();

    private DynamicAcrossValue(String name) {
        super(name);
    }

    public static DynamicAcrossValue create(String name) {
        return new DynamicAcrossValue(name);
    }

    public DynamicAcrossValue addValue(Integer value) {
        this.values.add(new JsonPrimitive(value));

        return this;
    }

    public DynamicAcrossValue addValue(String value) {
        this.values.add(new JsonPrimitive(value));

        return this;
    }

    public DynamicAcrossValue addValue(Boolean value) {
        this.values.add(new JsonPrimitive(value));

        return this;
    }
}
