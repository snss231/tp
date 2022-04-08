package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_EMAIL;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_GIT_USERNAME;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PHONE;
import static seedu.address.commons.core.Messages.MESSAGE_TAG_TOO_LONG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.DUPLICATE_ALICE_EMAIL;
import static seedu.address.testutil.TypicalPersons.DUPLICATE_ALICE_PHONE;
import static seedu.address.testutil.TypicalPersons.DUPLICATE_ALICE_USERNAME;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUsername;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.testutil.PersonBuilder;


public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        Model modelAcceptingPerson = new ModelManager();
        Person validPerson = new PersonBuilder().build();
        CommandResult commandResult = new AddCommand(validPerson).execute(modelAcceptingPerson);

        //Creating the equivalent list
        UniquePersonList validUniquePersonList = new UniquePersonList();
        validUniquePersonList.add(validPerson);
        ObservableList<Person> validList = validUniquePersonList.asUnmodifiableObservableList();

        //Successfully add Person
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());

        //Check that Valid Person exists in Model
        assertEquals(validList, modelAcceptingPerson.getFilteredPersonList());
    }

    @Test
    public void execute_invalidTagLength_throwsCommandException() {
        Model model = new ModelManager();
        ArrayList<Tag> list = new ArrayList<>();
        String invalidTag = "This tag is over 50 characters long..............................."
                + "............................................................................................";
        list.add(new Tag(invalidTag));
        Set<Tag> invalidTagSet = new HashSet<Tag>(list);

        Person invalidTagPerson = new PersonBuilder().withTags(invalidTag).build();
        AddCommand invalidAddCommand = new AddCommand(invalidTagPerson);
        assertThrows(CommandException.class,
                String.format(MESSAGE_TAG_TOO_LONG,
                        invalidTagSet.stream()
                                .filter(tag -> tag.tagName.length() > 50)
                                .reduce("", (str, tag) -> tag + "\n" + str, (s1, s2) -> s1 + s2)), ()
                -> invalidAddCommand.execute(model));
    }


    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = ALICE;
        AddCommand addCommand = new AddCommand(validPerson);

        //Check Username Error
        Model modelWithSameUsername = new ModelManager();
        modelWithSameUsername.addPerson(DUPLICATE_ALICE_USERNAME);
        assertThrows(CommandException.class,
                MESSAGE_DUPLICATE_GIT_USERNAME, () -> addCommand.execute(modelWithSameUsername));

        //Check Email Error
        Model modelWithSameEmail = new ModelManager();
        modelWithSameEmail.addPerson(DUPLICATE_ALICE_EMAIL);
        assertThrows(CommandException.class,
                MESSAGE_DUPLICATE_EMAIL, () -> addCommand.execute(modelWithSameEmail));

        //Check Phone Error
        Model modelWithSamePhone = new ModelManager();
        modelWithSamePhone.addPerson(DUPLICATE_ALICE_PHONE);
        assertThrows(CommandException.class,
                MESSAGE_DUPLICATE_PHONE, () -> addCommand.execute(modelWithSamePhone));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").withEmail("alice123@gmail.com").build();
        Person bob = new PersonBuilder().withName("Bob").withEmail("bob123@gmail.com").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskList(ReadOnlyTaskList taskList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEmail(Email email) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPhone(Phone phone) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task taskToEdit, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasUsername (GitUsername gitUsername) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void markTask(Task task) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void unmarkTask(Task task) {
            throw new AssertionError("This method should not be called");
        }

    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
