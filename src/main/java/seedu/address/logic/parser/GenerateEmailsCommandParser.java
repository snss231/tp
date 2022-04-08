package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GenerateEmailsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class GenerateEmailsCommandParser implements Parser<GenerateEmailsCommand> {

    @Override
    public GenerateEmailsCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new GenerateEmailsCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }
}
