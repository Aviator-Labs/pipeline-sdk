package org.aviatorlabs.ci.sdk.varsource.vault;

import org.aviatorlabs.ci.sdk.util.Validator;
import org.aviatorlabs.ci.sdk.varsource.AbstractVarSource;
import org.aviatorlabs.ci.sdk.varsource.VarSourceType;

public class VaultVarSource extends AbstractVarSource<VaultVarConfig> {
    private VaultVarSource(String name, VaultVarConfig config) {
        super(name, VarSourceType.VAULT, config);
    }

    public static VaultVarSource create(String name, String url) {
        Validator.validateIdentifier(name);

        if (url == null) {
            throw new IllegalArgumentException("URL cannot be null");
        }

        VaultVarConfig config = VaultVarConfig.create(url);

        return new VaultVarSource(name, config);
    }
}
