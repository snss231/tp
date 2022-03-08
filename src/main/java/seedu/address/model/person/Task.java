package seedu.address.model.person;

import java.time.LocalDateTime;


/**
 * Represents a Task in NUSClasses.
 * Task Name is immutable, dateTime is mutable with the edit command.
 */
public class Task {
    private String name;
    private LocalDateTime dateTime;

    public Task(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDateTime(LocalDateTime newDateTime) {
        this.dateTime = newDateTime;
    }
}
