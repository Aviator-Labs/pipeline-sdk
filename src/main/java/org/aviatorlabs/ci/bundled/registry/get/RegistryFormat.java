package org.aviatorlabs.ci.bundled.registry.get;

import com.google.gson.annotations.SerializedName;

public enum RegistryFormat {
    @SerializedName("rootfs")
    ROOTFS,
    @SerializedName("oci")
    OCI,
    @SerializedName("oci-layout")
    OCILAYOUT
}
