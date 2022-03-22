package seedu.address.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a Task in NUSClasses.
 * Task consists of a String object representing a name and a LocalDateTime object representing the date and time.
 */
public class Task {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, h.mm a");
    private String name;
    private LocalDateTime dateTime;
    private List<Person> people;
    private Tag tag;
    private Link link;

    /**
     * Constructor for Task.
     *
     * @param name Name of task
     * @param dateTime LocalDateTime object representing Date and Time for Task
     */
    public Task(String name, LocalDateTime dateTime, Tag tag, Link link) {
        this.name = name;
        this.dateTime = dateTime;
        this.people = new ArrayList<>();
        this.tag = tag;
        this.link = link;
    }

    /**
     * Constructor for Task with a list of people already provided.
     *
     * @param name Name of task
     * @param people People to be added to the list
     * @param dateTime LocalDateTime object representing Date and Time for Task
     * @param link Link to be added to the task
     */
    public Task(String name, LocalDateTime dateTime, List<Person> people, Tag tag, Link link) {
        this(name, dateTime, tag, link);
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
        return this.name + " " + this.dateTime.format(formatter);
    }

    public String getDateTimeString() {
        return this.dateTime.format(formatter);
    }

    /**
     * Returns DateTime of Task.
     *
     * @return DateTime object of Task.
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns name of Task.
     *
     * @return Name of Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns Tag of Task.
     *
     * @return Tag of Task.
     */
    public Tag getTag() {
        return this.tag;
    }

    /**
     * Returns list of People assigned to Task.
     *
     * @return List of People.
     */
    public List<Person> getPeople() {
        return this.people;
    }

    /**
     * Returns true if both tasks have the same name.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }
        return otherTask != null
                && otherTask.getName().equals(getName());
    }

    /**
     * Returns the number of people assigned to Task.
     *
     * @return Number of people.
     */
    public int getNoOfPeople() {
        return this.people.size();
    }

    /**
     * Returns the zoom link assigned to Task.
     *
     * @return A zoom link.
     */
    public Link getLink() {
        return link;
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
