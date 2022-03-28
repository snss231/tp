package seedu.address.logic.parser;

import seedu.address.logic.commands.FilterByDateCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskBetweenDatesPredicate;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindTaskCommand.ERROR_MESSAGE_INVALID_FORMAT;
import static seedu.address.logic.commands.FindTaskCommand.ERROR_MESSAGE_INVALID_TAG;

/**
 * Parses input arguments and creates a new FilterByDate Command
 */
public class FilterByDateTimeParser  implements Parser<FilterByDateCommand> {

    public FilterByDateCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FilterByDateCommand(new TaskBetweenDatesPredicate(inBetweenDates(trimmedArgs)));
    }

    /**
     * Gets the before and after date from "filter d/22-08-2022 0800,23-08-2023 0800"
     * @param dates dates to be seperated
     * @return A list of before and after dates
     */
    public List<LocalDateTime> inBetweenDates(String dates) throws DateTimeParseException, ParseException {
        // from "d/22-08-2022,23-08-2022" to ["d", "22-08-2022 0800", "23-08-2022 0800"]
        String[] splitDates = dates.split("[/,]");
        if (splitDates.length != 3) {
            throw new ParseException(ERROR_MESSAGE_INVALID_TAG);
        }

        /*
        trim() used to prevent whitespaces in " 23-08-2022 0800",
        which will cause the String array to be {"", "23", "08", "2022", "0800"},
        as we want only {"23", "08", "2022", "0800"}
        */
        String[] datetime1 = splitDates[1].trim().split("[ -]");
        String[] date2 = splitDates[2].trim().split("[ -]");
        Integer[] date1Int = new Integer[5];
        Integer[] date2Int = new Integer[5];
        // date1Int = [dd, mm, yyyy, HH, MM]
        for (int i = 0; i < datetime1.length; i++) {
            date1Int[i] = Integer.parseInt(datetime1[i]);
            date2Int[i] = Integer.parseInt(date2[i]);
            if (i == 3) {
                date1Int[i] = Integer.parseInt(datetime1[i].substring(0, 2));
                date1Int[i + 1] = Integer.parseInt(datetime1[i].substring(2, 4));
                date2Int[i] = Integer.parseInt(date2[i].substring(0, 2));
                date2Int[i + 1] = Integer.parseInt(date2[i].substring(2, 4));
            }
        }
        try {
            LocalDateTime firstDateTime = LocalDateTime.of(date1Int[2],
                    date1Int[1], date1Int[0], date1Int[3], date1Int[4]);
            LocalDateTime secondDateTime = LocalDateTime.of(date2Int[2],
                    date2Int[1], date2Int[0], date2Int[3], date2Int[4]);
            if (firstDateTime.isBefore(secondDateTime)) {
                LocalDateTime[] toReturn = {firstDateTime, secondDateTime};
                return Arrays.asList(toReturn);
            } else {
                LocalDateTime[] toReturn = {secondDateTime, firstDateTime};
                return Arrays.asList(toReturn);
            }
        } catch (DateTimeParseException e) {
            throw new ParseException(ERROR_MESSAGE_INVALID_FORMAT);
        }


    }
}
