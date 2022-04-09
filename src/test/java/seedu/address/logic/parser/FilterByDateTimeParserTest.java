package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindTaskCommand;

import static seedu.address.commons.core.Messages.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class FilterByDateTimeParserTest {


    private FilterByDateTimeParser parser = new FilterByDateTimeParser();

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
        assertParseFailure(parser, String.format(INVALID_DATETIME_DESC,"," ,VALID_DATETIME_TASKA_PLUS_ONE_DAY),
                ERROR_MESSAGE_INVALID_PARAMETER);

        // Words instead of numbers
        // assertParseFailure(parser, );

    }
}
