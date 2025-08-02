package org.aviatorlabs.ci.resource.git;

import lombok.Getter;
import org.aviatorlabs.ci.sdk.ISerializableEnum;

@Getter
public enum TagBehaviour implements ISerializableEnum {
    MATCH_TAGGED("match_tagged"),
    MATCH_TAG_ANCESTORS("match_tag_ancestors");

    private final String displayName;

    TagBehaviour(String displayName) {
        this.displayName = displayName;
    }
}
