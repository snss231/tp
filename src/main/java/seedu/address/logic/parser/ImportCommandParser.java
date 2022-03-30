package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
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

public class ImportCommandParser implements Parser<ImportCommand> {

    public static final String MESSAGE_CSV_MISSING_HEADERS = "Missing headers in the file %s";

    public static final String MESSAGE_CSV_MISSING_FIELD = "Error: found empty field in the file %s";

    public ImportCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILEPATH);

        if (!arePrefixesPresent(argMultimap, PREFIX_FILEPATH)) {
            throw new ParseException(ImportCommand.MESSAGE_USAGE);
        }

        Path path = ParserUtil.parsePath(argMultimap.getValue(PREFIX_FILEPATH));

        List<Person> toAdd = new ArrayList<>();

        try {
            Scanner sc = new Scanner(path.toFile());
            List<String> columns = Arrays.asList(sc.nextLine().split(","));
            int nameIndex = columns.indexOf("Name");
            int phoneIndex = columns.indexOf("Phone");
            int emailIndex = columns.indexOf("Email");
            int githubIndex = columns.indexOf("Github");
            int tagsIndex = columns.indexOf("Tags");

            if (List.of(nameIndex, phoneIndex, emailIndex, githubIndex, tagsIndex).contains(-1)) {
                throw new ParseException(String.format(MESSAGE_CSV_MISSING_FIELD, path.getFileName()));
            }

            while (sc.hasNextLine()) {
                String[] values = sc.nextLine().split(",");

                Name name = ParserUtil.parseName(values[nameIndex]);
                Phone phone = ParserUtil.parsePhone(values[phoneIndex]);
                Email email = ParserUtil.parseEmail(values[emailIndex]);
                GitUsername gitUsername = ParserUtil.parseGitUsername(values[githubIndex]);
                Set<Tag> tagList = ParserUtil.parseTags(Arrays.asList(values[tagsIndex].split("/")));

                toAdd.add(new Person(name, phone, email, gitUsername, tagList));
            }

        } catch (FileNotFoundException e) {
            throw new ParseException(String.format(MESSAGE_CSV_MISSING_HEADERS, path.getFileName()));
        }

        return new ImportCommand(toAdd, argMultimap.getValue(PREFIX_FILEPATH).get());
    }
}
