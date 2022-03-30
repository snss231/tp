package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FilterByDateCommand.ERROR_MESSAGE_INVALID_FORMAT;
import static seedu.address.logic.commands.FilterByDateCommand.ERROR_MESSAGE_INVALID_TAG;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import seedu.address.logic.commands.FilterByDateCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskBetweenDatesPredicate;
/**
 * Parses input arguments and creates a new FilterByDate Command
 */
public class FilterByDateTimeParser implements Parser<FilterByDateCommand> {

    private static final String DATE_TIME_PATTERN = "dd-MM-yyyy HHmm";
    private static final String DATE_ONLY_PATTERN = "dd-MM-yyyy";
    private SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATE_TIME_PATTERN);
    private SimpleDateFormat dateOnlyFormatter = new SimpleDateFormat(DATE_ONLY_PATTERN);

    /**
     * Parses the given {@code String} of arguments in the context of the FilterByDateTimeCommand
     * and returns a FilterByDateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterByDateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        return new FilterByDateCommand(new TaskBetweenDatesPredicate(inBetweenDates(trimmedArgs)));
    }

    /**
     * Gets the before and after date from "filter d/22-08-2022 0800,23-08-2023 0800"
     *
     * @param dates dates to be seperated
     * @return A list of before and after dates
     */
    public List<LocalDateTime> inBetweenDates(String dates) throws DateTimeParseException, ParseException {
        // from "d/22-08-2022,23-08-2022" to ["d", "22-08-2022 0800", "23-08-2022 0800"]
        String[] splitDates = dates.split("[/,]");
        if (splitDates.length != 3) {
            throw new ParseException(ERROR_MESSAGE_INVALID_TAG);
        }

        // check if time is provided
        if (gotTime(splitDates[1]) && gotTime(splitDates[2])) {
            return dayMonthYear(splitDates[1], splitDates[2]);
        } else {
            return dayMonthYearTime(splitDates[1], splitDates[2]);
        }
    }

    /**
     * Check if input contains time
     *
     * @param datetime String datetime input form user
     * @return true if date time contains time, else return false
     */
    private boolean gotTime(String datetime) {
        return datetime.trim().split("[- ]").length == 3;
    }

    /**
     * Converts user date only input into a list containing 2 date time elements
     *
     * @param datetime1 String of date without time in the format dd-MM-yyyy
     * @param datetime2 String of date without time in the format dd-MM-yyyy
     * @return List of date sorted, first date at 0000 hrs (lower bound) and second date at 2359 hrs (upper bound)
     * @throws ParseException Invalid date format
     */
    private List<LocalDateTime> dayMonthYear(String datetime1, String datetime2) throws ParseException {
        try {
            LocalDateTime ldt1 = convertToLocalDateTime(dateOnlyFormatter.parse(datetime1));
            LocalDateTime ldt2 = convertToLocalDateTime(dateOnlyFormatter.parse(datetime2));
            List<LocalDateTime> sortedLdt = localDateTimeChecker(ldt1, ldt2);
            LocalDateTime setUpperBoundTiming = sortedLdt.get(1).plusHours(23).plusMinutes(59);
            sortedLdt.set(1, setUpperBoundTiming);
            return sortedLdt;
        } catch (java.text.ParseException e) {
            throw new ParseException(ERROR_MESSAGE_INVALID_FORMAT);
        }
    }
    /**
     * Converts user date time input into a list containing 2 date time elements
     *
     * @param datetime1 String of date time in the format dd-MM-yyyy
     * @param datetime2 String of date time in the format dd-MM-yyyy HHmm
     * @return List of date sorted, first dt being lower bound, second dt being upper bound
     * @throws ParseException Invalid date format
     */
    private List<LocalDateTime> dayMonthYearTime(String datetime1, String datetime2) throws ParseException {
        try {
            LocalDateTime ldt1 = convertToLocalDateTime(dateTimeFormatter.parse(datetime1));
            LocalDateTime ldt2 = convertToLocalDateTime(dateTimeFormatter.parse(datetime2));
            return localDateTimeChecker(ldt1, ldt2);
        } catch (java.text.ParseException e) {
            throw new ParseException(ERROR_MESSAGE_INVALID_FORMAT);
        }
    }

    /**
     * Sort 2 given date time
     *
     * @param firstDateTime first date time to be sorted
     * @param secondDateTime second date time to be sorted
     * @return A list of date time, first dt being the earlier one, second dt being the later one
     */
    private List<LocalDateTime> localDateTimeChecker(LocalDateTime firstDateTime, LocalDateTime secondDateTime) {
        if (firstDateTime.isBefore(secondDateTime)) {
            LocalDateTime[] toReturn = {firstDateTime, secondDateTime};
            return Arrays.asList(toReturn);
        } else {
            LocalDateTime[] toReturn = {secondDateTime, firstDateTime};
            return Arrays.asList(toReturn);
        }
    }

    /**
     * Inspired from .\src\main\java\seedu\address\logic\parser\EditTaskCommandParser.java
     *
     * @param dateToConvert date to be converted
     * @return Local Date Time
     */
    private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }
}
