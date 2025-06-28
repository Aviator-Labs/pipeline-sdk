package org.aviatorlabs.ci.sdk.step;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.aviatorlabs.ci.bundled.git.GitResource;
import org.aviatorlabs.ci.bundled.git.GitResourceConfig;
import org.aviatorlabs.ci.bundled.git.get.GitGet;
import org.aviatorlabs.ci.bundled.mock.MockConfig;
import org.aviatorlabs.ci.bundled.mock.MockResourceType;
import org.aviatorlabs.ci.sdk.Pipeline;
import org.aviatorlabs.ci.sdk.TestUtils;
import org.aviatorlabs.ci.sdk.job.Job;
import org.aviatorlabs.ci.sdk.resource.AnonymousResource;
import org.aviatorlabs.ci.sdk.step.across.AbstractAcrossValue;
import org.aviatorlabs.ci.sdk.step.across.DynamicAcrossValue;
import org.aviatorlabs.ci.sdk.step.task.InputMapping;
import org.aviatorlabs.ci.sdk.step.task.OutputMapping;
import org.aviatorlabs.ci.sdk.step.task.Task;
import org.aviatorlabs.ci.sdk.step.task.config.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoTest {

    @Test
    void addStepInitializesSet() {
        // Arrange
        Do doStep = Do.create();

        SetPipeline setOne = SetPipeline.create("set-pipeline-one", "output/pipeline.yml");

        // Act
        doStep.addStep(setOne);

        // Assert
        assertEquals(1, doStep.getSteps().size());
    }

    @Test
    void chainAddSteps() {
        // Arrange
        Do doStep = Do.create();

        SetPipeline setOne = SetPipeline.create("set-pipeline-one", "output/pipeline.yml");
        SetPipeline setTwo = SetPipeline.create("set-pipeline-two", "output/pipeline.yml");

        // Act
        doStep.addStep(setOne).addStep(setTwo);

        // Assert
        assertEquals(2, doStep.getSteps().size());
    }

    @Test
    void doAcross() {
        // Arrange
        Pipeline pipeline = new Pipeline();
        Job job = new Job("job");

        AbstractAcrossValue variable = DynamicAcrossValue.create("name").addValue("Kaladin").addValue("Jasnah");
        Do doStep = Do.create().addAcrossVariable(variable);

        Command helloVariable = Command.createCommand("echo").addArg(String.format("Hello %s!", variable.getVariable()));
        TaskConfig sayHello = TaskConfig.create(Platform.LINUX, AnonymousResource.create(MockResourceType.create(), MockConfig.create().mirrorSelf()), helloVariable);
        Task hello = Task.create("saying-hello", sayHello);

        Command byeVariable = Command.createCommand("echo").addArg(String.format("Bye %s!", variable.getVariable()));
        TaskConfig sayBye = TaskConfig.create(Platform.LINUX, AnonymousResource.create(MockResourceType.create(), MockConfig.create().mirrorSelf()), byeVariable);
        Task bye = Task.create("saying-bye", sayBye);

        // Act
        doStep.addStep(hello).addStep(bye);
        job.addStep(doStep);
        pipeline.addJob(job);

        // Assert
        assertEquals(1, pipeline.getJobs().size());

        JsonElement generated = JsonParser.parseString(pipeline.render());
        JsonElement expected = TestUtils.loadFromAssets("across/do-across.json");

        assertEquals(expected, generated);
    }

    @Test
    void inputOutputAcross() {
        // Arrange
        Pipeline pipeline = new Pipeline();
        GitResource ciRepo = GitResource.create("ci", GitResourceConfig.create("https://github.com/concourse/examples.git"));
        pipeline.addResource(ciRepo);

        Job job = new Job("job");
        GitGet getDefinition = ciRepo.createGetDefinition();
        job.addStep(getDefinition);

        AbstractAcrossValue variable = DynamicAcrossValue.create("pipeline").addValue("hello-world").addValue("time-triggered");

        Do doStep = Do.create().addAcrossVariable(variable);

        AnonymousResource<MockConfig> mockResource = AnonymousResource.create(MockResourceType.create(), MockConfig.create().mirrorSelf());

        //   Task Definition - Running Task
        Command runningCommand = Command.createCommand("cat").addArg(String.format("%1$s/pipelines/%1$s.yml", variable.getVariable()));
        TaskConfig runningConfig = TaskConfig.create(mockResource, runningCommand);
        Task runningPipeline = Task.createUsingParent(String.format("running-%s", variable.getVariable()), runningConfig, doStep);
        InputMapping ciInputMapping = runningPipeline.addInputMapping(getDefinition, variable.getVariable());

        Output runningOutput = Output.createUsingParent(variable.getVariable(), doStep);

        OutputMapping ciOutputMapping = runningPipeline.addOutputMapping(runningOutput, "newci");
        runningConfig.addInput(Input.create(ciInputMapping));
        runningConfig.addOutput(runningOutput);

        //   Task Definition - NewCI Task
        Command newCiCommand = Command.createCommand("cat").addArg(String.format("newci/pipelines/%s.yml", variable.getVariable()));
        TaskConfig newCiConfig = TaskConfig.create(mockResource, newCiCommand);
        Task newCiPipeline = Task.createUsingParent(String.format("newci-%s", variable.getVariable()), newCiConfig, doStep);
        newCiConfig.addInput(Input.create(ciOutputMapping));

        doStep.addStep(runningPipeline).addStep(newCiPipeline);

        // Act

        job.addStep(doStep);
        pipeline.addJob(job);

        // Assert
        JsonElement generated = JsonParser.parseString(pipeline.render());
        JsonElement expected = TestUtils.loadFromAssets("across/input-output-across.json");

        assertEquals(expected, generated);
    }

}