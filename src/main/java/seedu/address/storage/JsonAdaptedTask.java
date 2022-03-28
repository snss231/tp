package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Link;
import seedu.address.model.task.Task;

public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String dateTime;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String link;
    private final String isTaskMarkDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("dateTime") String dateTime,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("link") String link,
                           @JsonProperty("isTaskMarkDone") String isTaskMarkDone) {
        this.name = name;
        this.dateTime = dateTime;
        this.link = link;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.isTaskMarkDone = isTaskMarkDone;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName();
        dateTime = source.getDateTime().toString();
        link = source.getLink().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        isTaskMarkDone = String.valueOf(source.isTaskMark());
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name"));
        }
        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name"));
        }
        LocalDateTime modelDateTime = LocalDateTime.parse(dateTime);

        Set<Tag> modelTag = new HashSet<>(taskTags);
        final Set<Tag> modelTags = new HashSet<>(taskTags);
        Link modelLink = new Link(link);
        boolean modelIsTaskMarkDone = Boolean.parseBoolean(isTaskMarkDone);

        return new Task(name, modelDateTime, modelTag, modelLink, modelIsTaskMarkDone);
    }
}
