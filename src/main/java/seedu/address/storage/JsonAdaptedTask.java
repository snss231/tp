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
    private final String tid;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("dateTime") String dateTime,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("link") String link,
                           @JsonProperty("tid") String tid) {
        this.name = name;
        this.dateTime = dateTime;
        this.link = link;
        this.tid = tid;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
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
        tid = String.valueOf(source.getTid());
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
        int modelTid = Integer.parseInt(tid);

        return new Task(name, modelDateTime, modelTag, modelLink, modelTid);
    }
}
