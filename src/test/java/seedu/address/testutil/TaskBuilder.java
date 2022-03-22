package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Link;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "SchoolWork";
    public static final LocalDateTime DEFAULT_DATETIME =
            LocalDateTime.of(2022, 12, 15, 21, 0);
    public static final String DEFAULT_TAG = "School";
    public static final String DEFAULT_ZOOMLINK = "";

    private String name;
    private LocalDateTime dateTime;
    private Set<Tag> tags;
    private Link link;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = DEFAULT_NAME;
        dateTime = DEFAULT_DATETIME;
        tags = new HashSet<>();
        link = new Link(DEFAULT_ZOOMLINK);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        dateTime = taskToCopy.getDateTime();
        tags = new HashSet<>(taskToCopy.getTags());
        link = taskToCopy.getLink();
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
    public TaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Task} that we are building.
     */
    public TaskBuilder withDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Task} that we are building.
     */
    public TaskBuilder withLink(String link) {
        this.link = new Link(link);
        return this;
    }


    public Task build() {
        return new Task(name, dateTime, tags, link);
    }

}
