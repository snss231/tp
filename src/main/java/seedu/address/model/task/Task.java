package seedu.address.model.task;

import java.time.LocalDateTime;

/**
 * Represents a Task in NUSClasses.
 * Task consists of a String object representing a name and a LocalDateTime object representing the date and time.
 */
public class Task {
    private String name;
    private LocalDateTime dateTime;

    /**
     * Constructor for Task.
     *
     * @param name Name of task
     * @param dateTime LocalDateTime object representing Date and Time for Task
     */
    public Task(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    /**
     * Changes name of Task.
     *
     * @param name new Name to be changed.
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * Changes DateTime of Task
     *
     * @param newDateTime LocalDateTime of new DateTime
     */
    public void changeDateTime(LocalDateTime newDateTime) {
        this.dateTime = newDateTime;
    }

    @Override
    public String toString() {
        return this.name + " " + this.dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String getName() {
        return this.name;
    }
}
