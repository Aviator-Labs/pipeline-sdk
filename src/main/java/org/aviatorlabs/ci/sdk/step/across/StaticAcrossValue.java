package org.aviatorlabs.ci.sdk.step.across;

public class StaticAcrossValue extends AbstractAcrossValue {
    private final String values;

    private StaticAcrossValue(String name, String values) {
        super(name);

        this.values = values;
    }

    public static StaticAcrossValue create(String name, String values) {
        return new StaticAcrossValue(name, values);
    }
}
