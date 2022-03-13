package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPersonToTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddPersonToTaskCommandParser implements Parser<AddPersonToTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonToTaskCommand
     * and returns an AddPersonToTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonToTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PERSON);

        Index taskIndex;
        Index personIndex;

        try {
            taskIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            personIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PERSON).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonToTaskCommand.MESSAGE_USAGE), pe);
        }

        return new AddPersonToTaskCommand(taskIndex, personIndex);
    }
}
