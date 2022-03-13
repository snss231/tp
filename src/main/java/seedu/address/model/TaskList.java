package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;

/**
 * Contains a list of Tasks
 */
public class TaskList implements Iterable<Task>, ReadOnlyTaskList {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public TaskList() {}

    /**
     * Creates a TaskList using the Tasks in the {@code toBeCopied}
     */
    public TaskList(ReadOnlyTaskList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks. //todo: really cannot contain duplicates?
     */
    public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        internalList.setAll(tasks);
    }


    /**
     * Resets the existing data of this {@code TaskList} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskList newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }


    /**
     * Adds a Task to the list.
     *
     * @param taskToAdd Task to be added.
     */
    public void addTask(Task taskToAdd) {
        requireNonNull(taskToAdd);
        this.internalList.add(taskToAdd);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.internalList.size(); i++) {
            output += this.internalList.get(i) + "\n";
        }
        return output;
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    /**
     * Deletes a Task to the list.
     *
     * @param taskToDelete Task to be deleted.
     */
    public void deleteCurrTask(Task taskToDelete) {
        requireNonNull(taskToDelete);
        this.internalList.remove(taskToDelete);
    }
}
