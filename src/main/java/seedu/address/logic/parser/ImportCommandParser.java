package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    public static final String MESSAGE_CSV_MISSING_HEADERS = "Missing headers in the file \"%s\"";

    public static final String MESSAGE_FOLDER_SPECIFIED = "Error: \"%s\" is a directory";

    public static final String MESSAGE_FILE_DOES_NOT_EXIST = "Error: could not find the file \"%s\".";

    public static final String ERROR_INVALID_NAME = "Error: the name \"%s\" is invalid: " + Name.MESSAGE_CONSTRAINTS;

    public static final String ERROR_INVALID_PHONE = "Error: the phone \"%s\" is invalid: " + Phone.MESSAGE_CONSTRAINTS;

    public static final String ERROR_INVALID_EMAIL = "Error: the email \"%s\" is invalid: "
            + Email.MESSAGE_CONSTRAINTS;

    public static final String ERROR_INVALID_GITHUB = "Error: the github username\"%s\" is invalid: "
            + GitUsername.MESSAGE_CONSTRAINTS;

    public static final String ERROR_INVALID_TAG = "Error: the tag\"%s\" is invalid: "
            + Tag.MESSAGE_CONSTRAINTS;



    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns a ImportCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILEPATH);

        if (!arePrefixesPresent(argMultimap, PREFIX_FILEPATH)) {
            throw new ParseException(ImportCommand.MESSAGE_USAGE);
        }

        File file = ParserUtil.parsePath(argMultimap.getValue(PREFIX_FILEPATH)).toFile();

        if (!file.exists()) {
            throw new ParseException(String.format(MESSAGE_FILE_DOES_NOT_EXIST, file.getPath()));
        }

        if (file.isDirectory()) {
            throw new ParseException(String.format(MESSAGE_FOLDER_SPECIFIED, file.getPath()));
        }

        List<Person> personToAddList = new ArrayList<>();

        List<String> invalidFields = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            List<String> columns = Arrays.asList(sc.nextLine().split(","));
            int nameIndex = columns.indexOf("Name");
            int phoneIndex = columns.indexOf("Phone");
            int emailIndex = columns.indexOf("Email");
            int githubIndex = columns.indexOf("Github");
            int tagsIndex = columns.indexOf("Tags");

            if (List.of(nameIndex, phoneIndex, emailIndex, githubIndex, tagsIndex).contains(-1)) {
                throw new ParseException(String.format(MESSAGE_CSV_MISSING_HEADERS, file.getPath()));
            }

            loop: while (sc.hasNextLine()) {
                String[] values = sc.nextLine().split(",");
                Name name;
                Phone phone;
                Email email;
                GitUsername gitUsername;
                Set<Tag> tags = new HashSet<>();

                try {
                    name = ParserUtil.parseName(values[nameIndex]);
                } catch (ParseException e) {
                    invalidFields.add(String.format(ERROR_INVALID_NAME, values[nameIndex]));
                    continue;
                }
                try {
                    phone = ParserUtil.parsePhone(values[phoneIndex]);
                } catch (ParseException e) {
                    invalidFields.add(String.format(ERROR_INVALID_PHONE, values[phoneIndex]));
                    continue;
                }
                try {
                    email = ParserUtil.parseEmail(values[emailIndex]);
                } catch (ParseException e) {
                    invalidFields.add(String.format(ERROR_INVALID_EMAIL, values[emailIndex]));
                    continue;
                }

                try {
                    gitUsername = ParserUtil.parseGitUsername(values[githubIndex]);
                } catch (ParseException e) {
                    invalidFields.add(String.format(ERROR_INVALID_GITHUB, values[githubIndex]));
                    continue;
                }

                for (String tag : values[tagsIndex].split("/")) {
                    try {
                        tags.add(ParserUtil.parseTag(tag));
                    } catch (ParseException e) {
                        invalidFields.add(String.format(ERROR_INVALID_TAG, tag));
                        continue loop;
                    }
                }

                personToAddList.add(new Person(name, phone, email, gitUsername, tags));
            }

        } catch (FileNotFoundException e) {
            throw new ParseException(String.format(MESSAGE_CSV_MISSING_HEADERS, file.getPath()));
        }

        return new ImportCommand(personToAddList, argMultimap.getValue(PREFIX_FILEPATH).get(), invalidFields);
    }
}
