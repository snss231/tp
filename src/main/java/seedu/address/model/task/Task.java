package seedu.address.model.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;

/**
 * Represents a Task in NUSClasses.
 * Task consists of a String object representing a name and a LocalDateTime object representing the date and time.
 */
public class Task {
    private String name;
    private LocalDateTime dateTime;
    private List<Person> people;

    /**
     * Constructor for Task.
     *
     * @param name Name of task
     * @param dateTime LocalDateTime object representing Date and Time for Task
     */
    public Task(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
        this.people = new ArrayList<>();
    }

    /**
     * Constructor for Task with a list of people already provided.
     *
     * @param name Name of task
     * @param people People to be added to the list
     * @param dateTime LocalDateTime object representing Date and Time for Task
     */
    public Task(String name, LocalDateTime dateTime, List<Person> people) {
        this(name, dateTime);
        this.people = new ArrayList<>(people);
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
     * Add a person to the list of people associated with the task.
     *
     * @param person Person to add
     */
    public void addPerson(Person person) {
        people.add(person);
    }

    public void removePerson(Person person) {
        people.remove(person);
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

    public List<Person> getPeople() {
        return this.people;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(this.getName())
                && otherTask.getDateTime().equals(this.getDateTime())
                && otherTask.getPeople().equals(this.getPeople());
    }
}
