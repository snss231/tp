package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Contains a list of Tasks
 */
public class TaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    /**
     * Adds a Task to the list.
     *
     * @param taskToAdd Task to be added.
     */
    public void add(Task taskToAdd) {
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

}
