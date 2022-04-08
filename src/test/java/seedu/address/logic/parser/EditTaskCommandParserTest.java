package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_NEED_AT_LEAST_ONE_VALID_PARAMETER;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_TASKA;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_TASKB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TASKA;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TASKA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TASKA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TASKB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASKA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TASKA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE);
    private final String errorMessage = String.format(MESSAGE_NEED_AT_LEAST_ONE_VALID_PARAMETER,
            EditTaskCommand.MESSAGE_USAGE);

    private EditTaskCommandParser parser = new EditTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {

        // no index specified
        assertParseFailure(parser, VALID_NAME_TASKA, errorMessage);

        // no field specified
        assertParseFailure(parser, "1", errorMessage);

        // no index and no field specified
        assertParseFailure(parser, "", errorMessage);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_TASKA, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_TASKA, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", errorMessage);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", errorMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // Invalid time format
        String dateTimeErrorMessage = String.format(MESSAGE_INVALID_DATETIME, EditTaskCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1 " + INVALID_DATETIME_DESC, dateTimeErrorMessage);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_TASKA + DATETIME_DESC_TASKB + TAG_DESC_TASKA;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(VALID_NAME_TASKA)
                .withDateTime(VALID_DATETIME_TASKB).withTags(VALID_TAG_TASKA).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_TASKA + DATETIME_DESC_TASKB;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(VALID_NAME_TASKA)
                .withDateTime(VALID_DATETIME_TASKB).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_TASK;
        String userInput = targetIndex.getOneBased() + NAME_DESC_TASKA;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(VALID_NAME_TASKA).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date time
        userInput = targetIndex.getOneBased() + DATETIME_DESC_TASKA;
        descriptor = new EditTaskDescriptorBuilder().withDateTime(VALID_DATETIME_TASKA).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_TASKA;
        descriptor = new EditTaskDescriptorBuilder().withTags(VALID_TAG_TASKA).build();
        expectedCommand = new EditTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + DATETIME_DESC_TASKA + DATETIME_DESC_TASKA
                + TAG_DESC_TASKA + DATETIME_DESC_TASKA + TAG_DESC_TASKA + NAME_DESC_TASKA;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(VALID_NAME_TASKA)
                .withDateTime(VALID_DATETIME_TASKA).withTags(VALID_TAG_TASKA).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
