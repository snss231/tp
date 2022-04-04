package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.TaskNotFoundException;

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

    /**
     * Replaces Task at index target in TaskList with editedTask.
     *
     * @param target index of task to be changed.
     * @param editedTask New task to replace the previous one.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        internalList.set(index, editedTask);
    }

    /**
     * Returns true if a task with the same description as {@code task} exists in the task list.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return internalList.contains(task);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && internalList.equals(((TaskList) other).internalList));

    }

    /**
     * Removes the person from each task if the task contains the person.
     * @param target the person to be removed.
     */
    public void removePerson(Person target) {
        internalList.forEach(task -> task.removePerson(target));
    }


    public void setPerson(Person target, Person editedPerson) {
        internalList.forEach(task-> task.updatePerson(target, editedPerson));
    }

    /**
     * Marks the task as completed and update the task list.
     *
     * @param task the task to be marked.
     */
    public void markTask(Task task) {
        requireAllNonNull(task);

        int index = internalList.indexOf(task);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        Task newTask = internalList.get(index);
        newTask.markTask();
        setTask(task, newTask);
    }

    /**
     * Unmarks the task as not complete and update the task list.
     *
     * @param task the task to be unmarked.
     */
    public void unmarkTask(Task task) {
        requireAllNonNull(task);

        int index = internalList.indexOf(task);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        Task newTask = internalList.get(index);
        newTask.unmarkTask();
        setTask(task, newTask);
    }

}
