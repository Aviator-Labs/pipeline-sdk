package org.aviatorlabs.ci.sdk.step.task;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.aviatorlabs.ci.bundled.git.GitResource;
import org.aviatorlabs.ci.bundled.git.GitResourceConfig;
import org.aviatorlabs.ci.bundled.git.get.GitGet;
import org.aviatorlabs.ci.bundled.mock.MockConfig;
import org.aviatorlabs.ci.bundled.mock.MockResourceType;
import org.aviatorlabs.ci.bundled.registry.RegistryImageConfig;
import org.aviatorlabs.ci.bundled.registry.RegistryImageResource;
import org.aviatorlabs.ci.sdk.Pipeline;
import org.aviatorlabs.ci.sdk.TestUtils;
import org.aviatorlabs.ci.sdk.job.Job;
import org.aviatorlabs.ci.sdk.resource.AnonymousResource;
import org.aviatorlabs.ci.sdk.step.across.AbstractAcrossValue;
import org.aviatorlabs.ci.sdk.step.across.DynamicAcrossValue;
import org.aviatorlabs.ci.sdk.step.task.config.Command;
import org.aviatorlabs.ci.sdk.step.task.config.Output;
import org.aviatorlabs.ci.sdk.step.task.config.Platform;
import org.aviatorlabs.ci.sdk.step.task.config.TaskConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void simpleTask() {
        // Arrange
        TaskConfig config = TaskConfig.create(Platform.LINUX, AnonymousResource.create("busybox"), Command.createCommand("sh").addArg("hello"));

        // Act
        Task task = Task.create("task", config);

        // Assert
        assertEquals("task", task.getTask());
        assertInstanceOf(RegistryImageConfig.class, task.getConfig().getResource().getConfig());

        RegistryImageConfig returnedConfig = (RegistryImageConfig) task.getConfig().getResource().getConfig();

        assertEquals("busybox", returnedConfig.getRepository());
    }

    @Test
    void taskWithYAMLTemplate() {
        // Arrange
        GitResourceConfig gitConfig = GitResourceConfig.create("https://git.my_domain.com/repo.git");
        GitResource resource = GitResource.create("repo", gitConfig);
        GitGet get = resource.createGetDefinition();

        // Act
        Task task = Task.create("task", get, "pipeline/templates/my_job.yml");

        // Assert
        assertEquals("task", task.getTask());
        assertNull(task.getConfig());
        assertEquals("repo/pipeline/templates/my_job.yml", task.getFile());
    }

    @Test
    void taskWithYAMLTemplateWithLeadingSlash() {
        // Arrange
        GitResourceConfig gitConfig = GitResourceConfig.create("https://git.my_domain.com/repo.git");
        GitResource resource = GitResource.create("repo", gitConfig);
        GitGet get = resource.createGetDefinition();

        // Act
        Task task = Task.create("task", get, "/pipeline/templates/my_second_job.yml");

        // Assert
        assertEquals("task", task.getTask());
        assertNull(task.getConfig());
        assertEquals("repo/pipeline/templates/my_second_job.yml", task.getFile());
    }

    @Test
    void taskWithNullPath() {
        // Arrange
        GitResourceConfig gitConfig = GitResourceConfig.create("https://git.my_domain.com/repo.git");
        GitResource resource = GitResource.create("repo", gitConfig);
        GitGet get = resource.createGetDefinition();

        // Assert
        assertThrows(RuntimeException.class, () -> Task.create("task", get, null));
    }

    @Test
    void specifyingTaskImage() {
        // Arrange
        RegistryImageConfig config = RegistryImageConfig.create("busybox");
        RegistryImageResource busyBox = RegistryImageResource.create("busy_box", config);

        Task task = Task.create("task", TaskConfig.create(Platform.LINUX, Command.createCommand("echo").addArg("Hello World")));

        // Act
        task.setImage(busyBox.createGetDefinition());

        // Assert
        assertNotNull(task.getImage());
        assertEquals("busy_box", task.getImage());
    }

    @Test
    void markPrivileged() {
        // Arrange
        TaskConfig config = TaskConfig.create(Platform.LINUX, AnonymousResource.create("busybox"), Command.createCommand("sh").addArg("hello"));
        Task task = Task.create("task", config);

        // Act
        task.markPrivileged();

        // Assert
        assertTrue(task.getPrivileged());
    }

    @Test
    void markHermetic() {
        // Arrange
        TaskConfig config = TaskConfig.create(Platform.LINUX, AnonymousResource.create("busybox"), Command.createCommand("sh").addArg("hello"));
        Task task = Task.create("task", config);

        // Act
        task.markHermetic();

        // Assert
        assertTrue(task.getIsHermetic());
    }

    @Test
    void addUnstructuredVariables() {
        // Arrange
        GitResourceConfig gitConfig = GitResourceConfig.create("https://git.my_domain.com/repo.git");
        GitResource resource = GitResource.create("repo", gitConfig);
        GitGet get = resource.createGetDefinition();
        Task task = Task.create("task", get, "/pipeline/templates/my_second_job.yml");

        // Act
        JsonObject object = new JsonObject();
        object.addProperty("sub_key", "sub_value");

        task.addVar("key", "value")
                .addVar("complex", object)
                .addVar("second", "second");

        // Assert
        assertNotNull(task.getVars());
        assertTrue(task.getVars().has("key"));
        assertEquals("value", task.getVars().get("key").getAsString());

        assertTrue(task.getVars().has("complex"));
        assertInstanceOf(JsonObject.class, task.getVars().get("complex"));
        assertTrue(task.getVars().get("complex").getAsJsonObject().has("sub_key"));
        assertEquals("sub_value", task.getVars().get("complex").getAsJsonObject().get("sub_key").getAsString());

        assertTrue(task.getVars().has("second"));
    }

    @Test
    void setCPULimit() {
        // Arrange
        TaskConfig config = TaskConfig.create(Platform.LINUX, AnonymousResource.create("busybox"), Command.createCommand("sh").addArg("hello"));
        Task task = Task.create("task", config);

        // Act
        task.setCPULimit(2);

        // Assert
        assertEquals(2, task.getLimits().getCpu());
        assertNull(task.getLimits().getMemory());
    }

    @Test
    void setMemoryLimits() {
        // Arrange
        TaskConfig config = TaskConfig.create(Platform.LINUX, AnonymousResource.create("busybox"), Command.createCommand("sh").addArg("hello"));
        Task task = Task.create("task", config);

        // Act
        task.setMemoryLimit(1024);

        // Assert
        assertEquals(1024, task.getLimits().getMemory());
        assertNull(task.getLimits().getCpu());
    }

    @Test
    void chainContainerLimits() {
        // Arrange
        TaskConfig config = TaskConfig.create(Platform.LINUX, AnonymousResource.create("busybox"), Command.createCommand("sh").addArg("hello"));
        Task task = Task.create("task", config);

        // Act
        task.setMemoryLimit(1024).setCPULimit(2);

        // Assert
        assertEquals(1024, task.getLimits().getMemory());
        assertEquals(2, task.getLimits().getCpu());
    }

    @Test
    void addEnvVarParameters() {
        // Arrange
        TaskConfig config = TaskConfig.create(Platform.LINUX, AnonymousResource.create("busybox"), Command.createCommand("sh").addArg("hello"));
        Task task = Task.create("task", config);

        // Act
        task.addParam("ECHO_ME", "Eat your fruits").addParam("ALSO_ME", "veggies");

        // Assert
        assertEquals(2, task.getParams().size());
        assertEquals("Eat your fruits", task.getParams().get("ECHO_ME"));
    }

    @Test
    void addInputMappingFromGet() {
        // Arrange
        GitResource resource = GitResource.create("repo", GitResourceConfig.create("https://git.website.com/group/repo.git"));
        GitGet get = resource.createGetDefinition();

        Task task = Task.create("task", TaskConfig.create(Platform.LINUX, AnonymousResource.create("busybox"), Command.createCommand("echo").addArg("Hello, world!")));

        // Act
        InputMapping mapping = task.addInputMapping(get, "main");

        // Assert
        assertEquals(1, task.getInputMapping().size());

        assertEquals("repo", task.getInputMapping().get("main"));

        assertEquals("repo", mapping.getName());
        assertEquals("main", mapping.getMappedName());
    }

    @Test
    void addInputMappingFromOutput() {
        // Arrange
        Output output = Output.create("repo");

        Task task = Task.create("task", TaskConfig.create(Platform.LINUX, AnonymousResource.create("busybox"), Command.createCommand("echo").addArg("Hello, world!")));

        // Act
        InputMapping mapping = task.addInputMapping(output, "main");

        // Assert
        assertEquals(1, task.getInputMapping().size());

        assertEquals("main", task.getInputMapping().get("repo"));

        assertEquals("repo", mapping.getName());
        assertEquals("main", mapping.getMappedName());
    }

    @Test
    void addOutputMapping() {
        // Arrange
        Output output = Output.create("repo");

        Task task = Task.create("task", TaskConfig.create(Platform.LINUX, AnonymousResource.create("busybox"), Command.createCommand("echo").addArg("Hello, world!")));

        // Act
        OutputMapping mapping = task.addOutputMapping(output, "main");

        // Assert
        assertEquals(1, task.getOutputMapping().size());

        assertEquals("main", task.getOutputMapping().get("repo"));

        assertEquals("repo", mapping.getName());
        assertEquals("main", mapping.getMappedName());
    }

    @Test
    void taskAcross() {
        // Arrange
        Pipeline pipeline = new Pipeline();
        Job job = new Job("job");

        AbstractAcrossValue acrossVariable = DynamicAcrossValue.create("some-text").addValue("hello-world").addValue("hello-concourse");

        AnonymousResource<MockConfig> resource = AnonymousResource.create(MockResourceType.create(), MockConfig.create().mirrorSelf());
        TaskConfig config = TaskConfig.create(resource, Command.createCommand("echo").addArg(acrossVariable.getVariable()));
        Task task = Task.createAcrossTask(String.format("running-%s", acrossVariable.getVariable()), config, acrossVariable);

        // Act
        job.addStep(task);
        pipeline.addJob(job);

        // Assert
        JsonElement generated = JsonParser.parseString(pipeline.render());
        JsonElement expected = TestUtils.loadFromAssets("across/task-across.json");

        assertEquals(expected, generated);
    }

}