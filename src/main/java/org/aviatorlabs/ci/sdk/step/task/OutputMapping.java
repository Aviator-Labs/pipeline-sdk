package org.aviatorlabs.ci.sdk.step.task;

import lombok.Getter;

@Getter
public class OutputMapping implements ITaskMapping {
    private final String name;
    private final String mappedName;

    OutputMapping(String name, String mappedName) {
        this.name = name;
        this.mappedName = mappedName;
    }
}
