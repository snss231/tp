package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GenerateEmailsCommand;

class GenerateEmailsCommandParserTest {
    @Test
    void parse_validIndex_success() {
        Index index = Index.fromOneBased(1);

        GenerateEmailsCommandParser parser = new GenerateEmailsCommandParser();

        assertParseSuccess(parser, "1", new GenerateEmailsCommand(index));
    }

    @Test
    void parse_nonPositiveIndex_failure() {
        GenerateEmailsCommandParser parser = new GenerateEmailsCommandParser();

        assertParseFailure(parser, "0", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    void parse_stringIndex_failure() {
        GenerateEmailsCommandParser parser = new GenerateEmailsCommandParser();

        assertParseFailure(parser, "abcd", MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
}
