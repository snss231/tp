package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;

public interface ReadOnlyTaskList {

    /**
     * Returns ObservableList object containing Tasks.
     *
     * @return ObservableList of Task objects representing a TaskList.
     */
    ObservableList<Task> getTaskList();

    /**
     * Add Task to the TaskList
     *
     * @param task Task to be added.
     */
    void addTask(Task task);
}
