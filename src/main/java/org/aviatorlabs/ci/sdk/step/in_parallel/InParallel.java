package org.aviatorlabs.ci.sdk.step.in_parallel;

import com.google.gson.annotations.SerializedName;
import org.aviatorlabs.ci.sdk.step.AbstractAcrossStep;
import org.aviatorlabs.ci.sdk.step.IStep;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InParallel extends AbstractAcrossStep<InParallel> implements IStep {
    @SerializedName("in_parallel")
    private final InParallelConfig config = new InParallelConfig();

    public InParallel addStep(IStep step) {
        this.config.addStep(step);

        return this;
    }

    public void setLimit(Integer limit) {
        this.config.setLimit(limit);
    }

    public void setFailFast(Boolean failFast) {
        this.config.setFailFast(failFast);
    }

    @Override
    protected InParallel getSelf() {
        return this;
    }
}
