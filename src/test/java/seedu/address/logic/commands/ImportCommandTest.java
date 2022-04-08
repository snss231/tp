package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_DUPLICATES_NOT_ADDED;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_FOUND_TAGS_TOO_LONG;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_INVALID_FIELDS;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_NO_CONTACTS_ADDED;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.ImportCommand.personListToString;

import java.util.List;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class ImportCommandTest {

    @Test
    void execute_onePersonAdded_success() throws Exception {
        Person p = new PersonBuilder().build();
        List<Person> toAdd = List.of(p);
        String filename = "data.csv";
        List<String> invalidFields = List.of();

        Model model = new ModelManager();

        assertFalse(model.hasPerson(p));

        CommandResult commandResult = new ImportCommand(toAdd, filename, invalidFields).execute(model);

        assertTrue(model.hasPerson(p));

        assertEquals(String.format(MESSAGE_SUCCESS, 1, filename) +
                personListToString(toAdd), commandResult.toString());
    }

    @Test
    void execute_noPeopleAdded_success() throws Exception {
        List<Person> toAdd = List.of();
        String filename = "data.csv";
        List<String> invalidFields = List.of();

        Model model = new ModelManager();

        CommandResult commandResult = new ImportCommand(toAdd, filename, invalidFields).execute(model);

        assertEquals(String.format(MESSAGE_NO_CONTACTS_ADDED, filename), commandResult.toString());
    }

    @Test
    void execute_duplicatesFound_success() throws Exception {
        Person p = new PersonBuilder().build();
        List<Person> toAdd = List.of(p, p);
        String filename = "data.csv";
        List<String> invalidFields = List.of();

        Model model = new ModelManager();

        assertFalse(model.hasPerson(p));
        CommandResult commandResult = new ImportCommand(toAdd, filename, invalidFields).execute(model);
        assertTrue(model.hasPerson(p));

        assertEquals(String.format(MESSAGE_SUCCESS, 1, filename)
                + personListToString(List.of(p))
                + String.format(MESSAGE_DUPLICATES_NOT_ADDED, 1)
                + personListToString(List.of(p)), commandResult.toString());
    }

    @Test
    void execute_alreadyInContacts_success() throws Exception {
        Person p = new PersonBuilder().build();
        List<Person> toAdd = List.of(p);
        String filename = "data.csv";
        List<String> invalidFields = List.of();

        Model model = new ModelManager();
        model.addPerson(p);

        assertTrue(model.hasPerson(p));
        CommandResult commandResult = new ImportCommand(toAdd, filename, invalidFields).execute(model);


        assertEquals(String.format(MESSAGE_NO_CONTACTS_ADDED, filename)
                + String.format(MESSAGE_DUPLICATES_NOT_ADDED, 1)
                + personListToString(List.of(p)), commandResult.toString());
    }

    @Test
    void execute_invalidFieldsFound_success() throws Exception {
        List<Person> toAdd = List.of();
        String filename = "data.csv";
        List<String> invalidFields = List.of("foofoo", "hehe");

        Model model = new ModelManager();

        CommandResult commandResult = new ImportCommand(toAdd, filename, invalidFields).execute(model);

        assertEquals(String.format(MESSAGE_NO_CONTACTS_ADDED, filename)
                + String.format(MESSAGE_INVALID_FIELDS, 2)
                + String.join("\n", invalidFields), commandResult.toString());
    }

    @Test
    void execute_tagTooLong_success() throws Exception {
        Person p = new PersonBuilder()
                .withTags("joiwjfaioewfjoawefjowefjoiwejfowjfwijojoaewfjaiowfjiaeowjfaowjfiowf").build();
        List<Person> toAdd = List.of(p);
        String filename = "data.csv";
        List<String> invalidFields = List.of();

        Model model = new ModelManager();

        CommandResult commandResult = new ImportCommand(toAdd, filename, invalidFields).execute(model);

        assertEquals(String.format(MESSAGE_NO_CONTACTS_ADDED, filename)
                + String.format(MESSAGE_FOUND_TAGS_TOO_LONG, 1)
                + personListToString(toAdd), commandResult.toString());
    }
}
