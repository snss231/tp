package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INTERVAL;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECURRENCE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECURRENCE_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_TASKA;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TASKA;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_TASKB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TASKA_PLUS_ONE_DAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASKB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CHORES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTasks.BRUSH_TEETH;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.TaskBuilder;


public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();
    private final String dateTimePattern = "dd-MM-yyyy HHmm";
    private final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(dateTimePattern);

    @Test
    public void parseValidFieldsPresentSuccess() throws Exception {

        //Completely valid fields
        assertParseSuccess(parser, NAME_DESC_TASKB + DATETIME_DESC_TASKA + " "
                + PREFIX_TAG + VALID_TAG_CHORES, new AddTaskCommand(BRUSH_TEETH));

        //Whitespace in front of name
        assertParseSuccess(parser, " " + PREFIX_TASKNAME + " " + VALID_NAME_TASKB + DATETIME_DESC_TASKA + " "
                        + PREFIX_TAG + VALID_TAG_CHORES, new AddTaskCommand(BRUSH_TEETH));

        //Valid fields of only TASKNAME and DATETIME
        Task simpleTask = new TaskBuilder().withTaskName(VALID_NAME_TASKB)
                .withDateTime(LocalDateTime.of(2050, 12, 15, 21, 0)).build();
        assertParseSuccess(parser, NAME_DESC_TASKB + DATETIME_DESC_TASKA, new AddTaskCommand(simpleTask));

        //Ability to parse all fields including recurrence
        AddTaskCommand testCommand = new AddTaskCommand(VALID_NAME_TASKB,
                LocalDateTime.of(2050, 12, 15, 21, 0),
                LocalDateTime.of(2050, 12, 16, 21, 0),
                SampleDataUtil.getTagSet(VALID_TAG_CHORES), new Link(""), 2, 2);

        assertParseSuccess(parser, " " + NAME_DESC_TASKB + DATETIME_DESC_TASKA
                + " " + VALID_DATETIME_TASKA_PLUS_ONE_DAY + " " + PREFIX_TAG + VALID_TAG_CHORES + " "
                + PREFIX_RECURRING + "2 2", testCommand);
    }

    @Test
    public void parseCompulsoryFieldMissingFailure() {
        //Missing Taskname
        assertParseFailure(parser, DATETIME_DESC_TASKA, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                missingTaskNameErrorMessage() + "\n" + AddTaskCommand.MESSAGE_USAGE));

        //Missing DateTime
        assertParseFailure(parser, NAME_DESC_TASKA, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                missingDateTimeErrorMessage() + "\n" + AddTaskCommand.MESSAGE_USAGE));

        //Missing both
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                missingBothErrorMessage() + "\n" + AddTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void invalidDateTimeFailure() {
        assertParseFailure(parser, NAME_DESC_TASKB + " " + INVALID_DATETIME_DESC,
                String.format(MESSAGE_INVALID_DATETIME, AddTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void invalidRecurrenceFailure() {
        //Interval of 0
        assertParseFailure(parser, NAME_DESC_TASKB + DATETIME_DESC_TASKA + " " + PREFIX_RECURRING + "0 2",
                String.format(MESSAGE_INVALID_RECURRENCE_INDEX, AddTaskCommand.MESSAGE_USAGE));

        //Negative interval
        assertParseFailure(parser, NAME_DESC_TASKB + DATETIME_DESC_TASKA + " " + PREFIX_RECURRING + "-2 2",
                String.format(MESSAGE_INVALID_RECURRENCE_INDEX, AddTaskCommand.MESSAGE_USAGE));

        //Recurrence of 0
        assertParseFailure(parser, NAME_DESC_TASKB + DATETIME_DESC_TASKA + " " + PREFIX_RECURRING + "2 0",
                String.format(MESSAGE_INVALID_RECURRENCE_INDEX, AddTaskCommand.MESSAGE_USAGE));

        //Negative Recurrence
        assertParseFailure(parser, NAME_DESC_TASKB + DATETIME_DESC_TASKA + " " + PREFIX_RECURRING + "2 -2",
                String.format(MESSAGE_INVALID_RECURRENCE_INDEX, AddTaskCommand.MESSAGE_USAGE));

        //Non Integer Interval
        assertParseFailure(parser, NAME_DESC_TASKB + DATETIME_DESC_TASKA + " " + PREFIX_RECURRING + "a 2",
                String.format(MESSAGE_INVALID_INTERVAL, AddTaskCommand.MESSAGE_USAGE));

        //Non Integer Recurrence
        assertParseFailure(parser, NAME_DESC_TASKB + DATETIME_DESC_TASKA + " " + PREFIX_RECURRING + "2 $",
                String.format(MESSAGE_INVALID_RECURRENCE, AddTaskCommand.MESSAGE_USAGE));

    }


    /**
     * Returns missing TaskName error message as thrown in multiple Parser classes.
     *
     * @return Error message
     */
    private String missingTaskNameErrorMessage() {
        return "Missing/Invalid parameters: tn/ ";
    }

    /**
     * Returns missing DateTime error message as thrown in multiple Parser classes.
     *
     * @return Error message
     */
    private String missingDateTimeErrorMessage() {
        return "Missing/Invalid parameters: dt/ ";
    }

    /**
     * Returns missing TaskName and DateTime error message as thrown in multiple Parser classes.
     *
     * @return Error message
     */
    private String missingBothErrorMessage() {
        return "Missing/Invalid parameters: tn/, dt/ ";
    }
}
