package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    private final String dateTimePattern = "dd-MM-yyyy HHmm";
    private final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(dateTimePattern);

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     *
     * @param args String object of user input to be parsed.
     * @return AddTaskCommand object
     * @throws ParseException If the input does not conform to the expected format.
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASKNAME, PREFIX_DATETIME);
        if (!arePrefixesPresent(argMultimap, PREFIX_TASKNAME, PREFIX_DATETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }
        String taskName = argMultimap.getValue(PREFIX_TASKNAME).get();
        String dateTimeString = argMultimap.getValue(PREFIX_DATETIME).get();
        LocalDateTime dateTime;
        try {
            dateTime = convertToLocalDateTime(dateTimeFormatter.parse(dateTimeString));
        } catch (java.text.ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        return new AddTaskCommand(taskName, dateTime);
    }


    LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }
}
