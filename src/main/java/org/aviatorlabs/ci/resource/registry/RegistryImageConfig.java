package org.aviatorlabs.ci.resource.registry;

import com.google.gson.annotations.SerializedName;
import org.aviatorlabs.ci.sdk.resource.IResourceConfig;
import lombok.Getter;

@Getter
public class RegistryImageConfig implements IResourceConfig {
    private final String repository;

    private final String tag;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("registry_mirror")
    private RegistryMirrorConfig registryMirror;

    protected RegistryImageConfig(String repository, String tag) {
        this.repository = repository;
        this.tag = tag;
    }

    public static RegistryImageConfig create(String repository) {
        return RegistryImageConfig.create(repository, null);
    }

    public static RegistryImageConfig create(String repository, String tag) {
        return new RegistryImageConfig(repository, tag);
    }

    public RegistryImageConfig setCredentials(String username, String password) {
        this.username = username;
        this.password = password;

        return this;
    }

    public RegistryImageConfig setRegistryMirror(RegistryMirrorConfig config) {
        this.registryMirror = config;

        return this;
    }
}
