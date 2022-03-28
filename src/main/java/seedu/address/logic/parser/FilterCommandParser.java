package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindTaskCommand.ERROR_MESSAGE_INVALID_FORMAT;
import static seedu.address.logic.commands.FindTaskCommand.ERROR_MESSAGE_INVALID_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FilterByDateCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskBetweenDatesPredicate;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterCommandParser
{

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTaskCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        // check if prefix are used
        for (String string: nameKeywords) {
            if (string.contains(PREFIX_DATE.toString())) {
                return new FilterByDateCommand(new TaskBetweenDatesPredicate(inBetweenDates(trimmedArgs)));
            }
        }

        return new FindTaskCommand(new TaskNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }



}
