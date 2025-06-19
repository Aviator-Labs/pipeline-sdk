package org.aviatorlabs.ci.sdk.step;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
public class Do extends AbstractAcrossStep<Do> implements IStep {

    @SerializedName("do")
    private Set<IStep> steps;

    private Do() {
    }

    public static Do create() {
        return new Do();
    }

    public Do addStep(IStep step) {
        if (this.steps == null) {
            this.steps = new LinkedHashSet<>();
        }

        this.steps.add(step);

        return this;
    }

    @Override
    protected Do getSelf() {
        return this;
    }
}
