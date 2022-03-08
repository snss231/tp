package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    public void add(Task taskToAdd) {
        requireNonNull(taskToAdd);
        this.internalList.add(taskToAdd);
    }

}
