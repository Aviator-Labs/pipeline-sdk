package org.aviatorlabs.ci.bundled.git;

import com.google.gson.annotations.SerializedName;

public class SshCredentials implements ISubModuleCredential {
    private final String url;

    @SerializedName("private_key")
    private final String privateKey;

    @SerializedName("private_key_passphrase")
    private String privateKeyPassphrase;

    public SshCredentials(String url, String privateKey) {
        this.url = url;
        this.privateKey = privateKey;
    }

    public void addPrivateKeyPassphrase(String passphrase) {
        this.privateKeyPassphrase = passphrase;
    }

    @Override
    public final boolean equals(Object object) {
        if (!(object instanceof SshCredentials that)) return false;

        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }
}
