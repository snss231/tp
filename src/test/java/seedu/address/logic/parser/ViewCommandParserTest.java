package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;

public class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        assertParseSuccess(parser, "1", new ViewCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        // Using an alphabets for index
        assertParseFailure(parser, "a", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        // Using a negative integer for index
        assertParseFailure(parser, "-1", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        // Using zero for index
        assertParseFailure(parser, "0", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        // Using special character for index
        assertParseFailure(parser, "&", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

}
