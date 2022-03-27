package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_ALL_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_COMPLETE_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_INCOMPLETE_TASK;

import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Lists the task from the task list based on prefix.
 */
public class ListTaskCommandParser implements Parser<ListTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListTaskCommand
     * and returns an ListTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LIST_ALL_TASK,
                        PREFIX_LIST_INCOMPLETE_TASK, PREFIX_LIST_COMPLETE_TASK);

        if (argMultimap.getValue(PREFIX_LIST_ALL_TASK).isPresent()) {
            return new ListTaskCommand("all");
        } else if (argMultimap.getValue(PREFIX_LIST_INCOMPLETE_TASK).isPresent()) {
            return new ListTaskCommand("nc");
        } else if (argMultimap.getValue(PREFIX_LIST_COMPLETE_TASK).isPresent()) {
            return new ListTaskCommand("c");
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListTaskCommand.MESSAGE_USAGE));
        }
    }
}
