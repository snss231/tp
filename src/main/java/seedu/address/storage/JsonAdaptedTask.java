package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.Objects;

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
    private final String endDateTime;
    private final String tag;
    private final String link;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("dateTime") String dateTime,
                           @JsonProperty("endDateTime") String endDateTime,
                           @JsonProperty("tag") String tag, @JsonProperty("link") String link) {
        this.name = name;
        this.dateTime = dateTime;
        this.endDateTime = endDateTime;
        this.tag = tag;
        this.link = link;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName();
        dateTime = source.getDateTime().toString();
        endDateTime = String.valueOf(source.getEndDateTime());
        tag = source.getTag().toString();
        link = source.getLink().toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name"));
        }
        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "dateTime"));
        }
        LocalDateTime modelDateTime = LocalDateTime.parse(dateTime);
        LocalDateTime modelEndDateTime = Objects.equals(endDateTime, "null") ? null : LocalDateTime.parse(endDateTime);
        Tag modelTag = new Tag(tag);
        Link modelLink = new Link(link);
        return new Task(name, modelDateTime, modelEndDateTime, modelTag, modelLink);
    }
}
