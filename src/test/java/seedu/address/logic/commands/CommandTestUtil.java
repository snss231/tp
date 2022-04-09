package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_USERNAME_AMY = "amy123";
    public static final String VALID_USERNAME_BOB = "bob123";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String USERNAME_DESC_AMY = " " + PREFIX_GIT_USERNAME + VALID_USERNAME_AMY;
    public static final String USERNAME_DESC_BOB = " " + PREFIX_GIT_USERNAME + VALID_USERNAME_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_PHONE_SHORT = " " + PREFIX_PHONE + "99";
    public static final String INVALID_PHONE_LONG = " " + PREFIX_PHONE + "99999999999999999";

    public static final String INVALID_EMAIL_LONG = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA@gmail.com";
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_EMAIL_DESC_LONG = " " + PREFIX_EMAIL + INVALID_EMAIL_LONG; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    //------------------------ For task--------------------------------------------------------
    public static final String VALID_NAME_TASKA = "Homework";
    public static final String VALID_NAME_TASKB = "Brush my teeth";
    public static final String VALID_LINK_TASKA = "https:google.com";
    public static final String VALID_LINK_TASKB = "https:apple.com";
    public static final String INVALID_LINK = "a";
    public static final LocalDateTime VALID_DATETIME_TASKA =
            LocalDateTime.of(2050, 12, 15, 21, 0);
    public static final String VALID_DATETIME_TASKA_PLUS_ONE_DAY = "16-12-2050 2100";
    public static final LocalDateTime VALID_DATETIME_TASKB =
            LocalDateTime.of(2050, 02, 05, 13, 0);
    public static final String VALID_TAG_TASKA = "Schoolwork";
    public static final String VALID_TAG_TASKB = "Toilet";
    public static final String VALID_TAG_CHORES = "Chores";
    public static final String INVALID_TASK_NAME_SHORT = "A";
    public static final String INVALID_TASK_NAME_LONG = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
            + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    public static final EditTaskCommand.EditTaskDescriptor TASK_A;
    public static final EditTaskCommand.EditTaskDescriptor TASK_B;

    public static final String NAME_DESC_TASKA = " " + PREFIX_TASKNAME + VALID_NAME_TASKA;
    public static final String NAME_DESC_TASKB = " " + PREFIX_TASKNAME + VALID_NAME_TASKB;
    public static final String DATETIME_DESC_TASKA = " " + PREFIX_DATETIME + "15-12-2050 2100";
    public static final String DATETIME_DESC_TASKB = " " + PREFIX_DATETIME + "05-02-2050 1300";
    public static final String TAG_DESC_TASKA = " " + PREFIX_TAG + VALID_TAG_TASKA;
    public static final String TAG_DESC_TASKB = " " + PREFIX_TAG + VALID_TAG_TASKB;

    public static final String INVALID_DATETIME_DESC = PREFIX_DATETIME + "22/11/2050 1220"; // Wrong format

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withUsername(VALID_USERNAME_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withUsername(VALID_USERNAME_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        TASK_A = new EditTaskDescriptorBuilder().withTaskName(VALID_NAME_TASKA)
                .withDateTime(VALID_DATETIME_TASKA).withTags(VALID_TAG_TASKA).build();
        TASK_B = new EditTaskDescriptorBuilder().withTaskName(VALID_NAME_TASKB)
                .withDateTime(VALID_DATETIME_TASKB).withTags(VALID_TAG_TASKB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s Task List.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());
        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String taskName = task.getName();
        String[] arrTaskName = taskName.split(" ");
        model.updateFilteredTaskList(new TaskNameContainsKeywordsPredicate(Arrays.asList(arrTaskName)));
        assertEquals(1, model.getFilteredTaskList().size());
    }

}
