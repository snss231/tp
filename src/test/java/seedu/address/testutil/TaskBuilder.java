package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "SchoolWork";
    public static final LocalDateTime DEFAULT_DATETIME =
            LocalDateTime.of(2022, 12, 15, 21, 0);
    public static final String DEFAULT_TAG = "School";

    private String name;
    private LocalDateTime dateTime;
    private Tag tag;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = DEFAULT_NAME;
        dateTime = DEFAULT_DATETIME;
        tag = new Tag(DEFAULT_TAG);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        dateTime = taskToCopy.getDateTime();
        tag = taskToCopy.getTag();
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Parses the {@code tag} into a {@code Tag} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String tag) {
        this.tag = new Tag(tag);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Task} that we are building.
     */
    public TaskBuilder withDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Task build() {
        return new Task(name, dateTime, tag);
    }

}
