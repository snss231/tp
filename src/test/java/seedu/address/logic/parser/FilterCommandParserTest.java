package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_NO_KEYWORDS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_NO_KEYWORDS,
                FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTaskCommand() {

        FindTaskCommand expectedCommand =
                new FindTaskCommand(new TaskNameContainsKeywordsPredicate(Arrays.asList("Brush", "students")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "Brush students", expectedCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Brush \n \t students  \t", expectedCommand);
    }
}
