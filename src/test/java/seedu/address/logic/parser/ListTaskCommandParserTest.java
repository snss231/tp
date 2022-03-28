package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListTaskCommand;

public class ListTaskCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListTaskCommand.MESSAGE_USAGE);

    private ListTaskCommandParser parser = new ListTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // Wrong input
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // Wrong prefix
        assertParseFailure(parser, "all", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_correctPrefix_success() {
        String userInputAll = " all/";
        ListTaskCommand expectedCommandAll = new ListTaskCommand("all");
        assertParseSuccess(parser, userInputAll, expectedCommandAll);

        String userInputNC = " nc/";
        ListTaskCommand expectedCommandNC = new ListTaskCommand("nc");
        assertParseSuccess(parser, userInputNC, expectedCommandNC);

        String userInputC = " c/";
        ListTaskCommand expectedCommandC = new ListTaskCommand("c");
        assertParseSuccess(parser, userInputC, expectedCommandC);
    }


}
