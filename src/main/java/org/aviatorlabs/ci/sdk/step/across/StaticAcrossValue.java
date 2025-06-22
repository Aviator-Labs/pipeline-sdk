package org.aviatorlabs.ci.sdk.step.across;

import org.aviatorlabs.ci.sdk.step.var.LoadVar;

public class StaticAcrossValue extends AbstractAcrossValue {
    private final String values;

    private StaticAcrossValue(String name, String values) {
        super(name);

        this.values = values;
    }

    public static StaticAcrossValue create(String name, LoadVar variable) {
        return new StaticAcrossValue(name, variable.getLocalVariable());
    }
}
