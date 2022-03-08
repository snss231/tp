package seedu.address.model;

import seedu.address.model.person.TaskList;

/**
 * Object representing Tasks in the Model component. Contains a TaskList
 */
public class Tasks {
    private TaskList taskList;

    /**
     * Constructor for Tasks
     *
     * @param taskList internal TaskList
     */
    public Tasks(TaskList taskList) {
        this.taskList = taskList;
    }
}
