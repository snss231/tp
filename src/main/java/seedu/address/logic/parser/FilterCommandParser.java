package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FilterByDateCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskBetweenDatesPredicate;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        // check if prefix are used
        for (String string: nameKeywords) {
            if (string.contains(PREFIX_DATE.toString())) {
                return new FilterByDateCommand(new TaskBetweenDatesPredicate(inBetweenDates(nameKeywords[1])));
            }
        }

        return new FilterCommand(new TaskNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

    /**
     * Gets the before and after date from "filter d/22-08-2022 0800,23-08-2023 0800"
     * @param dates dates to be seperated
     * @return A list of before and after dates
     */
    public List<LocalDateTime> inBetweenDates(String dates) {
        // from "d/22-08-2022,23-08-2022" to ["d", "22-08-2022 0800", "23-08-2022 0800"]
        String[] splitDates = dates.split("[/,]");
        String[] datetime1 = splitDates[1].split("[ -]");
        String[] date2 = splitDates[2].split("[ -]");
        Integer[] date1Int = new Integer[5];
        Integer[] date2Int = new Integer[5];
        // date1Int = [dd, mm, yyyy, HH, MM]
        for (int i = 0; i < datetime1.length; i++) {
            date1Int[i] = Integer.parseInt(datetime1[i]);
            date2Int[i] = Integer.parseInt(date2[i]);
            if (i == 3) {
                date1Int[i] = Integer.parseInt(datetime1[i].substring(0 ,2));
                date1Int[i + 1] = Integer.parseInt(datetime1[i].substring(2 ,4));
                date2Int[i] = Integer.parseInt(date2[i].substring(0 ,2));
                date2Int[i + 1] = Integer.parseInt(date2[i].substring(2 ,4));
            }
        }
        LocalDateTime firstDateTime = LocalDateTime.of(date1Int[2]
                ,date1Int[1], date1Int[0], date1Int[3], date1Int[4]);
        LocalDateTime secondDateTime = LocalDateTime.of(date2Int[2]
                ,date2Int[1], date2Int[0], date2Int[3], date2Int[4]);
        if (firstDateTime.isBefore(secondDateTime)) {
            LocalDateTime[] toReturn = {firstDateTime, secondDateTime};
            return Arrays.asList(toReturn);
        } else {
            LocalDateTime[] toReturn = {secondDateTime, firstDateTime};
            return Arrays.asList(toReturn);
        }
    }

}
