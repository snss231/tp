package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FilterByDateCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.model.task.TaskBetweenDatesPredicate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static seedu.address.commons.core.Messages.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class FilterByDateTimeParserTest {


    private FilterByDateTimeParser parser = new FilterByDateTimeParser();
    private String toConcat = "," + VALID_DATETIME_TASKA_STRING;


    @Test
    public void parse_missingParts_failure() {

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
/*
// start here
// issue: keep returning different object
    @Test
    public void parse_validValue_success() {

        String userInput = DATETIME_DESC_TASKA + toConcat;
        FilterByDateCommand expectedCommand =  new FilterByDateCommand(new TaskBetweenDatesPredicate(
                Arrays.asList(VALID_DATETIME_TASKA, VALID_DATETIME_TASKA)));
        // both dates have time
        assertParseSuccess(parser, userInput, expectedCommand);
    }

*/
}
