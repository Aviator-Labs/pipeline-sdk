package org.aviatorlabs.ci.bundled.git;

import com.google.gson.annotations.SerializedName;
import org.aviatorlabs.ci.sdk.resource.IResourceConfig;

import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class GitResourceConfig implements IResourceConfig {
    private final URI uri;

    private final String branch;

    @SerializedName("private_key")
    private String privateKey;

    @SerializedName("private_key_user")
    private String privateKeyUser;

    @SerializedName("private_key_passphrase")
    private String privateKeyPassphrase;

    @SerializedName("forward_agent")
    private String forwardAgent;

    private String username;

    private String password;

    private Set<String> paths;

    @SerializedName("ignore_paths")
    private Set<String> ignorePaths;

    @SerializedName("skip_ssl_verification")
    private Boolean skipSSLVerification;

    @SerializedName("tag_filter")
    private String tagFilter;

    @SerializedName("tag_regex")
    private String tagRegex;

    private TagBehaviour tagBehaviour;

    @SerializedName("fetch_tags")
    private Boolean fetchTags;

    @SerializedName("submodule_credentials")
    private Set<ISubModuleCredential> subModuleCredentials;

    @SerializedName("git_config")
    private Map<String, String> gitConfig;

    @SerializedName("disable_ci_skip")
    private Boolean disableCISkip;

    protected GitResourceConfig(URI uri, String branch) {
        this.uri = uri;
        this.branch = branch;
    }

    public static GitResourceConfig create(String uri) {
        return create(uri, null);
    }

    public static GitResourceConfig create(String uri, String branch) {
        return new GitResourceConfig(URI.create(uri), branch);
    }

    public GitResourceConfig setHttpsCredentials(String username, String password) {
        this.username = username;
        this.password = password;

        return this;
    }

    public GitResourceConfig addPath(String path) {
        if (this.paths == null) {
            this.paths = new LinkedHashSet<>();
        }

        this.paths.add(path);

        return this;
    }

    public GitResourceConfig setIgnorePaths(String path) {
        if (this.ignorePaths == null) {
            this.ignorePaths = new LinkedHashSet<>();
        }

        this.ignorePaths.add(path);

        return this;
    }

    public GitResourceConfig skipSSLVerification() {
        this.skipSSLVerification = true;

        return this;
    }

    public GitResourceConfig setTagFilter(String tagFilter) {
        this.tagFilter = tagFilter;

        this.tagRegex = null;

        return this;
    }

    public GitResourceConfig setTagRegex(String tagRegex) {
        this.tagRegex = tagRegex;

        this.tagFilter = null;

        return this;
    }

    public GitResourceConfig fetchTags() {
        this.fetchTags = true;

        return this;
    }
}
