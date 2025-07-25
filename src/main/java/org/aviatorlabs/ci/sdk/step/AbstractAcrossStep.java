package org.aviatorlabs.ci.sdk.step;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.aviatorlabs.ci.sdk.step.across.AbstractAcrossValue;

import java.util.HashSet;
import java.util.Set;

@Getter
public abstract class AbstractAcrossStep<T extends IStep> extends AbstractStep<T> {
    private Set<AbstractAcrossValue> across;

    @SerializedName("failFast")
    private Boolean failFast;

    public T markAcrossFailFast() {
        if (this.across == null || this.across.isEmpty()) {
            throw new UnsupportedOperationException("One across variable must be present before setting fail fast");
        }

        this.failFast = true;

        return getSelf();
    }

    public T addAcrossVariable(AbstractAcrossValue variable) {
        if (this.across == null) {
            this.across = new HashSet<>();
        }

        this.across.add(variable);

        return getSelf();
    }
}
