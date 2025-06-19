package org.aviatorlabs.ci.sdk.step;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.aviatorlabs.ci.bundled.git.GitResource;
import org.aviatorlabs.ci.bundled.git.GitResourceConfig;
import org.aviatorlabs.ci.bundled.git.get.GitGet;
import org.aviatorlabs.ci.sdk.Pipeline;
import org.aviatorlabs.ci.sdk.TestUtils;
import org.aviatorlabs.ci.sdk.job.Job;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetPipelineTest {

    @Test
    void setPipelineAcross() {
        // Arrange
        Pipeline pipeline = new Pipeline();
        GitResource ci = GitResource.create("ci", GitResourceConfig.create("https://github.com/concourse/examples.git"));
        pipeline.addResource(ci);

        Job job = new Job("job");
        GitGet getDefinition = ci.createGetDefinition();
        job.addStep(getDefinition);

        AcrossVariable variable = AcrossVariable.create("pipeline").addValue("hello-world").addValue("time-triggered");

        String pipelineFile = String.format("pipelines/%s.yml", variable.getVariable());
        SetPipeline setPipeline = SetPipeline.createAcrossPipeline(variable.getVariable(), getDefinition, pipelineFile, variable);

        // Act
        job.addStep(setPipeline);
        pipeline.addJob(job);

        // Assert
        JsonElement generated = JsonParser.parseString(pipeline.render());
        JsonElement expected = TestUtils.loadFromAssets("across/set-pipeline-across.json");

        assertEquals(expected, generated);
    }

    @Test
    void failFastAcrossSteps() {
        // Arrange
        AcrossVariable variable = AcrossVariable.create("pipeline").addValue("hello-world").addValue("time-triggered");

        String pipelineFile = String.format("pipelines/%s.yml", variable.getVariable());
        SetPipeline setPipeline = SetPipeline.createAcrossPipeline(variable.getVariable(), pipelineFile, variable);

        // Act
        setPipeline.markAcrossFailFast();

        // Assert
        assertTrue(setPipeline.getFailFast());
    }

    @Test
    void failFastAcrossStepsFailsWithNoVar() {
        // Arrange
        SetPipeline setPipeline = SetPipeline.create("pipeline", "pipelineFile.yml");

        // Act
        assertThrows(UnsupportedOperationException.class, setPipeline::markAcrossFailFast);
    }
}