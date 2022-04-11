package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

/**
 * An Immutable TaskList that is serializable to JSON format.
 */
@JsonRootName(value = "tasks")
public class JsonSerializableTaskList {

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTasks} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableTaskList(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyTasks} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTasks}.
     */
    public JsonSerializableTaskList(ReadOnlyTaskList source) {
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Tasks} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskList toModelType() throws IllegalValueException {
        TaskList taskList = new TaskList();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            taskList.addTask(task);
        }
        return taskList;
    }
}
