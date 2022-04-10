package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.ERROR_MESSAGE_INVALID_FORMAT;
import static seedu.address.commons.core.Messages.ERROR_MESSAGE_INVALID_PARAMETER;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_TASKA;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_DAY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_LEAPYEAR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_VALUE_NOTIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_WORDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_NOTIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_NOTIME_LATER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_NOTIME_LATER_STRING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_NOTIME_STRING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TASKA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TASKA_STRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterByDateCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.model.task.TaskBetweenDatesPredicate;

public class FilterByDateTimeParserTest {


    private FilterByDateTimeParser parser = new FilterByDateTimeParser();
    private String toConcat = "," + VALID_DATETIME_TASKA_STRING;


    @Test
    public void parse_missingParts_failure() {

        // empty args
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTaskCommand.MESSAGE_USAGE));

        // missing date inputs
        assertParseFailure(parser, String.valueOf(PREFIX_DATETIME), ERROR_MESSAGE_INVALID_PARAMETER);

        // only 1 date input
        assertParseFailure(parser, DATETIME_DESC_TASKA,
                ERROR_MESSAGE_INVALID_PARAMETER);
    }

    @Test
    public void parse_invalidValue_failure() {


        // Invalid time format
        assertParseFailure(parser, INVALID_DATETIME_FORMAT + toConcat,
                ERROR_MESSAGE_INVALID_PARAMETER);

        // Words instead of numbers
        assertParseFailure(parser, INVALID_DATETIME_WORDS + toConcat,
                ERROR_MESSAGE_INVALID_FORMAT);

        // Wrong Day
        assertParseFailure(parser, INVALID_DATETIME_DAY + toConcat,
                ERROR_MESSAGE_INVALID_FORMAT);

        // Wrong Month
        assertParseFailure(parser, INVALID_DATETIME_MONTH + toConcat,
                ERROR_MESSAGE_INVALID_FORMAT);

        // Wrong Leap Year
        assertParseFailure(parser, INVALID_DATETIME_LEAPYEAR + toConcat,
                ERROR_MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_validValueBothHaveDateTime_success() {
        String userInput = DATETIME_DESC_TASKA + toConcat;
        FilterByDateCommand expectedCommand = new FilterByDateCommand(new TaskBetweenDatesPredicate(
                Arrays.asList(VALID_DATETIME_TASKA, VALID_DATETIME_TASKA)));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validValueFirstNoTime_success() {

        // dt/[earlier_date_noTime],[later_date_gotTime]
        String userInput = PREFIX_DATETIME + VALID_DATETIME_NOTIME_STRING + toConcat;
        FilterByDateCommand expectedCommand = new FilterByDateCommand(new TaskBetweenDatesPredicate(
                Arrays.asList(VALID_DATETIME_NOTIME, VALID_DATETIME_TASKA)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // dt/[later_date_noTime],[earlier_date_gotTime]
        userInput = PREFIX_DATETIME + VALID_DATETIME_TASKA_STRING + "," + VALID_DATETIME_NOTIME_STRING;
        expectedCommand = new FilterByDateCommand(new TaskBetweenDatesPredicate(
                Arrays.asList(VALID_DATETIME_NOTIME, VALID_DATETIME_TASKA)));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validValueSecondNoTime_success() {

        // dt/[earlier_date_gotTime],[later_date_noTime]
        String userInput = PREFIX_DATETIME + VALID_DATETIME_TASKA_STRING + "," + VALID_DATETIME_NOTIME_LATER_STRING;
        FilterByDateCommand expectedCommand = new FilterByDateCommand(new TaskBetweenDatesPredicate(
                Arrays.asList(VALID_DATETIME_TASKA, VALID_DATETIME_NOTIME_LATER)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // dt/[later_date_gotTime],[earlier_date_noTime]
        userInput = PREFIX_DATETIME + VALID_DATETIME_TASKA_STRING + "," + VALID_DATETIME_NOTIME_STRING;
        expectedCommand = new FilterByDateCommand(new TaskBetweenDatesPredicate(
                Arrays.asList(VALID_DATETIME_NOTIME, VALID_DATETIME_TASKA)));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validValueBothNoTime_success() {

        // dt/[earlier_date_noTime],later_date_noTime]
        String userInput = PREFIX_DATETIME + VALID_DATETIME_NOTIME_STRING + "," + VALID_DATETIME_NOTIME_LATER_STRING;
        FilterByDateCommand expectedCommand = new FilterByDateCommand(new TaskBetweenDatesPredicate(
                Arrays.asList(VALID_DATETIME_NOTIME, VALID_DATETIME_NOTIME_LATER)));
        assertParseSuccess(parser, userInput, expectedCommand);

        // dt/[later_date_noTime],[earlier_date_noTime]
        userInput = PREFIX_DATETIME + VALID_DATETIME_NOTIME_LATER_STRING + "," + VALID_DATETIME_NOTIME_STRING;
        expectedCommand = new FilterByDateCommand(new TaskBetweenDatesPredicate(
                Arrays.asList(VALID_DATETIME_NOTIME, VALID_DATETIME_NOTIME_LATER)));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueNoTime_failure() {
        assertParseFailure(parser, DATETIME_DESC_TASKA + "," + INVALID_DATETIME_VALUE_NOTIME,
                ERROR_MESSAGE_INVALID_FORMAT);
    }



}
