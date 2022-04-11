package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Link;
import seedu.address.model.task.Task;

public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String dateTime;
    private final String endDateTime;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedPerson> people = new ArrayList<>();
    private final String link;
    private final String isTaskMarkDone;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name,
                           @JsonProperty("dateTime") String dateTime,
                           @JsonProperty("endDateTime") String endDateTime,
                           @JsonProperty("people") List<JsonAdaptedPerson> people,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("link") String link,
                           @JsonProperty("isTaskMarkDone") String isTaskMarkDone) {
        this.name = name;
        this.dateTime = dateTime;
        this.endDateTime = endDateTime;
        this.people.addAll(people);
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
        dateTime = String.valueOf(source.getDateTime());
        endDateTime = String.valueOf(source.getEndDateTime());
        link = source.getLink().toString();
        people.addAll(source.getPeople().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
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

        List<Person> modelPeople = new ArrayList<>();
        for (JsonAdaptedPerson person : people) {
            modelPeople.add(person.toModelType());
        }


        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name"));
        }

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "dateTime"));
        }
        LocalDateTime modelDateTime = LocalDateTime.parse(dateTime);

        LocalDateTime modelEndDateTime = Objects.equals(endDateTime, "null") ? null : LocalDateTime.parse(endDateTime);

        Set<Tag> modelTag = new HashSet<>(taskTags);

        Link modelLink = Objects.equals(link, null) ? new Link() : new Link(link);
        if (!modelLink.isEmpty()) {
            if (!Link.isValidLink(modelLink.toString())) {
                throw new IllegalValueException(Link.MESSAGE_CONSTRAINTS);
            }
        }
        if (isTaskMarkDone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "isTaskMarkDone"));
        }

        boolean modelIsTaskMarkDone = Boolean.parseBoolean(isTaskMarkDone);

        return new Task(name, modelDateTime, modelEndDateTime, modelPeople, modelTag, modelLink, modelIsTaskMarkDone);
    }
}
