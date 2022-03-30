package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.io.CharacterEscapes;
import seedu.address.commons.util.TagUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = ": Imports contacts into NUS Classes from a .csv data file. "
            + "Parameters: "
            + PREFIX_FILEPATH + "FILEPATH\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FILEPATH + "data.csv";
    public static final String MESSAGE_SUCCESS =
            "Import completed. The following %d contact(s) were successfully added to NUS Classes from %s:\n";
    public static final String MESSAGE_DUPLICATES_NOT_ADDED =
            "\nNote: The following %d contact(s) containing duplicate fields were not added:\n";
    public static final String MESSAGE_FOUND_TAGS_TOO_LONG =
            "\nNote: The following %d contact(s) containing tags longer "
                    + "than the maximum length of 50 characters were not added:\n";
    private static final String MESSAGE_NO_CONTACTS_ADDED =
            "Import completed. No contacts were added to NUS Classes from %s.\n";

    private final String filename;
    private final List<Person> toAdd;

    public ImportCommand(List<Person> toAdd, String filename) {
        this.toAdd = toAdd;
        this.filename = filename;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> added = new ArrayList<>();
        int addedCount = 0;
        List<Person> duplicateContacts = new ArrayList<>();
        int duplicateCount = 0;
        List<Person> invalidTagContacts = new ArrayList<>();
        int invalidTagCount = 0;

        for (Person p : toAdd) {
            if (model.hasPerson(p)) {
                duplicateCount++;
                duplicateContacts.add(p);
                continue;
            }

            //null value represents no tags are too long.
            if (TagUtil.checkTagLength(p.getTags()) != null) {
                invalidTagCount++;
                invalidTagContacts.add(p);
                continue;
            }
            addedCount++;
            added.add(p);
            model.addPerson(p);
        }

        String infoAdded = addedCount == 0 ? String.format(MESSAGE_NO_CONTACTS_ADDED, filename)
                : String.format(MESSAGE_SUCCESS, addedCount, filename)
                + String.join("\n", () -> added.stream().<CharSequence>map(Person::toString).iterator());

        String infoDuplicates = duplicateCount == 0 ? "" : String.format(MESSAGE_DUPLICATES_NOT_ADDED, duplicateCount)
                + String.join("\n", () -> duplicateContacts.stream().<CharSequence>map(Person::toString).iterator());

        String infoInvalidTags = invalidTagCount == 0 ? "" : String.format(MESSAGE_FOUND_TAGS_TOO_LONG, invalidTagCount)
                + String.join("\n", () -> invalidTagContacts.stream().<CharSequence>map(Person::toString).iterator());


        return new CommandResult(infoAdded + infoDuplicates + infoInvalidTags);
    }
}