package org.aviatorlabs.ci.sdk.varsource.dummy;

import com.google.gson.JsonObject;
import org.aviatorlabs.ci.sdk.varsource.IVarSourceConfig;
import lombok.Getter;

@Getter
public class DummyVarConfig implements IVarSourceConfig {
    private JsonObject vars;

    public DummyVarConfig() {}

    public DummyVarConfig addVar(String key, String value) {
        if (vars == null) {
            vars = new JsonObject();
        }

        vars.addProperty(key, value);

        return this;
    }

    public DummyVarConfig addVar(String key, JsonObject value) {
        if (vars == null) {
            vars = new JsonObject();
        }

        vars.add(key, value);

        return this;
    }
}
