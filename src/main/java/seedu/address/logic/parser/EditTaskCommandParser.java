package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditTaskCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String dateTimePattern = "dd-MM-yyyy HHmm";
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(dateTimePattern);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASKNAME, PREFIX_DATETIME, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TASKNAME).isPresent()) {
            editTaskDescriptor.setName(argMultimap.getValue(PREFIX_TASKNAME).get());
        }
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            try {
                String dateTimeString = argMultimap.getValue(PREFIX_DATETIME).get();
                if (dateTimeString.contains(",")) {
                    String[] splits = dateTimeString.split(",");
                    editTaskDescriptor.setDate(convertToLocalDateTime(dateTimeFormatter.parse(splits[0])));
                    editTaskDescriptor.setEndDate(convertToLocalDateTime(dateTimeFormatter.parse(splits[1])));
                } else {
                    editTaskDescriptor.setDate(convertToLocalDateTime(dateTimeFormatter.parse(dateTimeString)));
                    editTaskDescriptor.setEndDate(null);
                }
            } catch (java.text.ParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE));
            }
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            editTaskDescriptor.setTags(ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
    }

    private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }

}
