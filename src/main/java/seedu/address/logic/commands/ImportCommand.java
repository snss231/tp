package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.TagUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Imports a list of contacts from a .csv file.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports contacts into "
            + "NUS Classes from a .csv data file. "
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
    public static final String MESSAGE_NO_CONTACTS_ADDED =
            "Import completed. No contacts were added to NUS Classes from %s.\n";
    public static final String MESSAGE_INVALID_FIELDS =
            "\nNote: %d contact(s) containing invalid fields were not added due to these issues:\n";

    private final String filename;
    private final List<Person> contactsToAdd;
    private final List<String> invalidFields;

    /**
     * Creates an ImportCommand with the following fields
     * @param contactsToAdd The contacts to be added to the model (before duplicate checks)
     * @param filename The name of the data file
     * @param invalidFields A list of strings, each giving info on an invalid entry that was provided in the data file.
     */
    public ImportCommand(List<Person> contactsToAdd, String filename, List<String> invalidFields) {
        this.contactsToAdd = contactsToAdd;
        this.filename = filename;
        this.invalidFields = invalidFields;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> personAddedContacts = new ArrayList<>();
        int addedCount = 0;
        List<Person> duplicateContacts = new ArrayList<>();
        int duplicateCount = 0;
        List<Person> invalidTagContacts = new ArrayList<>();
        int invalidTagCount = 0;

        for (Person p : contactsToAdd) {
            boolean hasEmail = model.hasEmail(p.getEmail());
            boolean hasPhone = model.hasPhone(p.getPhone());
            boolean hasUsername = model.hasUsername(p.getUsername());

            if (hasEmail || hasPhone || hasUsername) {
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
            personAddedContacts.add(p);
            model.addPerson(p);
        }

        String infoAdded = addedCount == 0
                ? String.format(MESSAGE_NO_CONTACTS_ADDED, filename)
                : String.format(MESSAGE_SUCCESS, addedCount, filename) + personListToString(personAddedContacts);

        String infoDuplicates = duplicateCount == 0
                ? ""
                : String.format(MESSAGE_DUPLICATES_NOT_ADDED, duplicateCount) + personListToString(duplicateContacts);

        String infoInvalidTags = invalidTagCount == 0
                ? ""
                : String.format(MESSAGE_FOUND_TAGS_TOO_LONG, invalidTagCount) + personListToString(invalidTagContacts);

        int invalidCount = invalidFields.size();

        String infoInvalidFields = invalidCount == 0
                ? ""
                : String.format(MESSAGE_INVALID_FIELDS, invalidCount) + String.join("\n", invalidFields);

        return new CommandResult(infoAdded + infoDuplicates + infoInvalidTags + infoInvalidFields);
    }

    /**
     * Converts a list of people to a string separated by a \n character.
     *
     * @param people The people to be converted.
     * @return The result string.
     */
    public static String personListToString(List<Person> people) {
        return String.join("\n", () -> people.stream().<CharSequence>map(Person::toString).iterator()) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImportCommand)) {
            return false;
        }
        ImportCommand other = (ImportCommand) o;
        return contactsToAdd.equals(other.contactsToAdd)
            && filename.equals(other.filename)
            && invalidFields.equals(other.invalidFields);
    }
}
