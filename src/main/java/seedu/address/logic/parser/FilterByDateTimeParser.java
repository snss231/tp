package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FilterByDateCommand.ERROR_MESSAGE_INVALID_FORMAT;
import static seedu.address.logic.commands.FilterByDateCommand.ERROR_MESSAGE_INVALID_PARAMETER;

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
     * Gets the before and after date from a String of dates, e.g. "filter dt/22-08-2022 0800,23-08-2023 0800"
     *
     * @param dates dates to be seperated
     * @return A list of before and after dates
     */
    public List<LocalDateTime> inBetweenDates(String dates) throws DateTimeParseException, ParseException {
        // from "dt/22-08-2022 0800,23-08-2022 0800" to ["dt", "22-08-2022 0800", "23-08-2022 0800"]
        String[] splitDates = dates.split("[/,]");
        if (splitDates.length != 3) {
            throw new ParseException(ERROR_MESSAGE_INVALID_PARAMETER);
        }


        // check if time is provided
        if (checkTime(splitDates[1]) && checkTime(splitDates[2])) {
            return localDateTimeChecker(dayMonthYearTime(splitDates[1]), dayMonthYearTime(splitDates[2]));
        } else if (!checkTime(splitDates[1]) && !checkTime(splitDates[2])) {
            return timeAdder(localDateTimeChecker(dayMonthYear(splitDates[1]), dayMonthYear(splitDates[2])));
        } else if (checkTime(splitDates[1]) && !checkTime(splitDates[2])) {
            return localDateTimeTargetedAdder(dayMonthYearTime(splitDates[1]),
                    dayMonthYear(splitDates[2]), true);
        } else if (!checkTime(splitDates[1]) && checkTime(splitDates[2])) {
            return localDateTimeTargetedAdder(dayMonthYear(splitDates[1]),
                    dayMonthYearTime(splitDates[2]), false);
        } else {
            throw new ParseException(ERROR_MESSAGE_INVALID_PARAMETER);
        }
    }

    /**
     * Check if input contains time
     *
     * @param datetime String datetime input form user
     * @return true if date time contains time, else return false
     */
    private boolean checkTime(String datetime) {
        return datetime.trim().split("[- ]").length == 4;
    }

    /**
     * Converts user date only input into a list containing 2 date time elements
     *
     * @param datetime String of date without time in the format dd-MM-yyyy
     * @return List of date sorted, first date at 0000 hrs (lower bound) and second date at 2359 hrs (upper bound)
     * @throws ParseException Invalid date format
     */
    private LocalDateTime dayMonthYear(String datetime) throws ParseException {
        try {
            dateOnlyFormatter.setLenient(false);
            return convertToLocalDateTime(dateOnlyFormatter.parse(datetime));
        } catch (java.text.ParseException e) {
            throw new ParseException(ERROR_MESSAGE_INVALID_FORMAT);
        }
    }
    /**
     * Converts user date time input into a list containing 2 date time elements
     *
     * @param datetime String of date time in the format dd-MM-yyyy HHmm
     * @return List of date sorted, first dt being lower bound, second dt being upper bound
     * @throws ParseException Invalid date format
     */
    private LocalDateTime dayMonthYearTime(String datetime) throws ParseException {
        try {
            dateTimeFormatter.setLenient(false);
            return convertToLocalDateTime(dateTimeFormatter.parse(datetime));
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
            return Arrays.asList(firstDateTime, secondDateTime);
        } else {
            return Arrays.asList(secondDateTime, firstDateTime);
        }
    }

    /**
     * Sort 2 given date time, where only 1 of them have time
     *
     * @param firstDateTime first date time to be sorted
     * @param secondDateTime second date time to be sorted
     * @param firstDateContainsTime is true if first date contains time
     * @return A list of date time, first dt being the earlier one, second dt being the later one
     * @throws ParseException Invalid date time format
     */
    private List<LocalDateTime> localDateTimeTargetedAdder(LocalDateTime firstDateTime,
                                                           LocalDateTime secondDateTime,
                                                           boolean firstDateContainsTime) throws ParseException {
        if (firstDateTime.isBefore(secondDateTime) && firstDateContainsTime) {
            return Arrays.asList(firstDateTime, secondDateTime.plusHours(23).plusMinutes(59));
        } else if (firstDateTime.isBefore(secondDateTime) && !firstDateContainsTime) {
            return Arrays.asList(firstDateTime, secondDateTime);
        } else if (!firstDateTime.isBefore(secondDateTime) && firstDateContainsTime) {
            return Arrays.asList(secondDateTime, firstDateTime);
        } else if (!firstDateTime.isBefore(secondDateTime) && !firstDateContainsTime) {
            return Arrays.asList(secondDateTime, firstDateTime.plusHours(23).plusMinutes(59));
        } else {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Adds upperbound time 23 hours and 59 min, in order to give it the property of full day search
     * e.g. dt/21-02-2022, 22-02-2022 -> 21 Feb 2022 12mn, 22 Feb 2022 11:59pm
     *
     * @param listOfDates Sorted date time list
     * @return Sorted date time list with proper time
     */
    private List<LocalDateTime> timeAdder(List<LocalDateTime> listOfDates) {
        LocalDateTime setUpperBoundTiming = listOfDates.get(1).plusHours(23).plusMinutes(59);
        listOfDates.set(1, setUpperBoundTiming);
        return listOfDates;
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
