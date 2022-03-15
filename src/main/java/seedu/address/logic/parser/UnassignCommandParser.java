package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnassignCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnassignCommandParser implements Parser<UnassignCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnassignCommand
     * and returns an UnassignCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnassignCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PERSON);

        Index taskIndex;
        Index personIndex;

        if (!arePrefixesPresent(argMultimap, PREFIX_PERSON)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE));
        }

        taskIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        personIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PERSON).get());

        return new UnassignCommand(taskIndex, personIndex);
    }
}
