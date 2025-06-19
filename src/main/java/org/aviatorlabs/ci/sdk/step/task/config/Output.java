package org.aviatorlabs.ci.sdk.step.task.config;

import org.aviatorlabs.ci.sdk.step.AbstractAcrossStep;
import org.aviatorlabs.ci.sdk.step.IStep;
import org.aviatorlabs.ci.sdk.step.task.OutputMapping;
import org.aviatorlabs.ci.sdk.util.Validator;
import lombok.Getter;

public class Output {
    @Getter
    private final String name;

    private String path;

    private Output(String name, Boolean validate) {
        if (validate) {
            Validator.validateIdentifier(name);
        }

        this.name = name;
    }

    public static Output create(String name) {
        return new Output(name, true);
    }

    public static Output create(OutputMapping mapping) {
        return new Output(mapping.getMappedName(), true);
    }

    public static Output createUsingParent(String name, AbstractAcrossStep<? extends IStep> parentStep) {
        if (parentStep.getAcross() == null || parentStep.getAcross().isEmpty()) {
            return Output.create(name);
        }

        return new Output(name, false);
    }

    public static Output createUsingParent(OutputMapping mapping, AbstractAcrossStep<? extends IStep> parentStep) {
        if (parentStep.getAcross() == null || parentStep.getAcross().isEmpty()) {
            return Output.create(mapping);
        }

        return new Output(mapping.getMappedName(), false);
    }

    public Output setPath(String path) {
        // TODO: Validate Dir-Path

        this.path = path;

        return this;
    }
}
