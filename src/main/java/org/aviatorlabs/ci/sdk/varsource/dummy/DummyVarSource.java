package org.aviatorlabs.ci.sdk.varsource.dummy;

import org.aviatorlabs.ci.sdk.util.Validator;
import org.aviatorlabs.ci.sdk.varsource.AbstractVarSource;
import org.aviatorlabs.ci.sdk.varsource.VarSourceType;

public class DummyVarSource extends AbstractVarSource<DummyVarConfig> {
    private DummyVarSource(String name, DummyVarConfig config) {
        super(name, VarSourceType.DUMMY, config);
    }

    public static DummyVarSource create(String name) {
        Validator.validateIdentifier(name);

        return new DummyVarSource(name, new DummyVarConfig());
    }
}
