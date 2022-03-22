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
    private LocalDateTime endDateTime;
    private List<Person> people;
    private Tag tag;
    private Link link;

    /**
     * Constructor for Task.
     *
     * @param name Name of task
     * @param dateTime LocalDateTime object representing Date and Time for Task
     * @param link Link to be added to the task
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
     * Constructor for task with endDateTime but no people.
     */
    public Task(String name, LocalDateTime dateTime, LocalDateTime endDateTime, Tag tag, Link link) {
        this(name, dateTime, tag, link);
        this.endDateTime = endDateTime;
    }

    /**
     * Constructor for task with endDateTime and people.
     */
    public Task(String name, LocalDateTime dateTime, LocalDateTime endDateTime,
                List<Person> people, Tag tag, Link link) {
        this(name, dateTime, people, tag, link);
        this.endDateTime = endDateTime;
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

    /**
     * Checks if this task has an end date time or not.
     * @return true if this task has an end date time, false otherwise.
     */
    public boolean hasEndDateTime() {
        return this.endDateTime != null;
    }

    /**
     * Returns a user-friendly representation of the dateTime.
     */
    public String getDateTimeString() {
        return this.dateTime.format(formatter);
    }

    /**
     * Returns a user-friendly representation of the endDateTime.
     */
    public String getEndDateTimeString() {
        return this.endDateTime.format(formatter);
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
     * Returns endDateTime of Task.
     *
     * @return endDateTime object of Task.
     */
    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
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
     * Checks if this task contains the person.
     * @param p the person to check for
     * @return true if this task contains the person, false otherwise.
     */
    public boolean containsPerson(Person p) {
        return this.people.contains(p);
    }

    /**
     * Returns the zoom link assigned to Task.
     *
     * @return A link.
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


    public boolean hasInvalidDateRange() {
        return endDateTime != null && dateTime.compareTo(endDateTime) >= 0;
    }
}
