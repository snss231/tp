package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.CONSULTATION;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Link;

public class JsonAdaptedTaskTest {
    private static final String INVALID_LINK = "a.com";
    private static final String INVALID_TAG = "£";
    private static final String INVALID_DATETIME = "28/15/2022";

    // Task name will always be valid
    private static final String TASKNAME = CONSULTATION.getName();

    private static final String VALID_LINK = CONSULTATION.getLink().toString();
    private static final String VALID_DATETIME = CONSULTATION.getDateTime().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = CONSULTATION.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_nullTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DATETIME, "null",
                new ArrayList<>(), VALID_TAGS, VALID_LINK, "false");
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name");
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(TASKNAME, null, "null",
                new ArrayList<>(), VALID_TAGS, VALID_LINK, "false");
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "dateTime");
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(CONSULTATION);
        assertEquals(CONSULTATION, task.toModelType());
    }

    @Test
    public void toModelType_invalidLink_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(TASKNAME, VALID_DATETIME, "null",
                        new ArrayList<>(), VALID_TAGS, INVALID_LINK, "false");

        String expectedMessage = Link.MESSAGE_CONSTRAINTS;

        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(TASKNAME, INVALID_DATETIME, "null",
                        new ArrayList<>(), VALID_TAGS, VALID_LINK, "false");

        assertThrows(DateTimeParseException.class, task::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));

        JsonAdaptedTask task =
                new JsonAdaptedTask(TASKNAME, VALID_DATETIME, "null",
                        new ArrayList<>(), invalidTags, VALID_LINK, "false");

        assertThrows(IllegalValueException.class, task::toModelType);
    }


}
