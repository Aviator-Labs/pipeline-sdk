package org.aviatorlabs.ci.sdk.step.task.config;

import org.aviatorlabs.ci.sdk.resource.get.Get;
import org.aviatorlabs.ci.sdk.step.task.InputMapping;
import org.aviatorlabs.ci.sdk.step.task.OutputMapping;

public class Input {
    private final String name;
    private String path;
    private Boolean optional;

    private Input(String name) {
        this.name = name;
    }

    public static Input create(Get get) {
        return new Input(get.getIdentifier());
    }

    public static Input create(Output output) {
        return new Input(output.getName());
    }

    public static Input create(InputMapping mapping) {
        return new Input(mapping.getMappedName());
    }

    public static Input create(OutputMapping mapping) {
        return new Input(mapping.getMappedName());
    }

    public Input setPath(String path) {
        // TODO: Validate Dir-Path

        this.path = path;

        return this;
    }

    public Input markOptional() {
        this.optional = true;

        return this;
    }
}
