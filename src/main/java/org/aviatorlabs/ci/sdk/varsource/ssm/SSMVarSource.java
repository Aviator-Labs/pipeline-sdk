package org.aviatorlabs.ci.sdk.varsource.ssm;

import org.aviatorlabs.ci.sdk.util.Validator;
import org.aviatorlabs.ci.sdk.varsource.AbstractVarSource;
import org.aviatorlabs.ci.sdk.varsource.VarSourceType;

public class SSMVarSource extends AbstractVarSource<SSMVarConfig> {
    private SSMVarSource(String name, SSMVarConfig config) {
        super(name, VarSourceType.SSM, config);
    }

    public static SSMVarSource create(String name, String region) {
        Validator.validateIdentifier(name);

        if (region == null) {
            throw new IllegalArgumentException("Config cannot be null");
        }

        SSMVarConfig config = new SSMVarConfig(region);

        return new SSMVarSource(name, config);
    }
}