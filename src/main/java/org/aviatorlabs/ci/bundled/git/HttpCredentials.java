package org.aviatorlabs.ci.bundled.git;

public class HttpCredentials implements ISubModuleCredential {
    private final String host;

    private final String username;

    private final String password;

    public HttpCredentials(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    @Override
    public final boolean equals(Object object) {
        if (!(object instanceof HttpCredentials that)) return false;

        return host.equals(that.host);
    }

    @Override
    public int hashCode() {
        return host.hashCode();
    }
}
