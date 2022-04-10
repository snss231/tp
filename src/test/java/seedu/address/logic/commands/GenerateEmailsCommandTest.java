package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.logic.commands.GenerateEmailsCommand.MESSAGE_GENERATED_EMAILS;
import static seedu.address.logic.commands.GenerateEmailsCommand.MESSAGE_NO_CONTACTS_ASSIGNED;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TypicalAssignedTasks;

class GenerateEmailsCommandTest {

    @Test
    void execute_validIndex_success() throws CommandException {
        TaskList sampleTasks = TypicalAssignedTasks.getTypicalAssignedTaskList();
        Index index = Index.fromOneBased(1);

        Model model = new ModelManager(new AddressBook(), new UserPrefs(), sampleTasks);

        GenerateEmailsCommand command = new GenerateEmailsCommand(index);

        Task source = sampleTasks.getTaskList().get(index.getZeroBased());

        String expected = String.format(MESSAGE_GENERATED_EMAILS, source, source.getEmails());
        String actual = command.execute(model).toString();

        assertEquals(expected, actual);
    }

    @Test
    void execute_outOfBoundsIndex_failure() {
        TaskList sampleTasks = TypicalAssignedTasks.getTypicalAssignedTaskList();
        Index index = Index.fromOneBased(99);

        Model model = new ModelManager(new AddressBook(), new UserPrefs(), sampleTasks);

        GenerateEmailsCommand command = new GenerateEmailsCommand(index);

        assertThrows(CommandException.class, MESSAGE_INVALID_TASK_DISPLAYED_INDEX, () -> command.execute(model));
    }

    @Test
    void execute_noContactsAssigned_failure() throws CommandException {
        TaskList sampleTasks = new TaskList();
        Task noContactsAssigned = new TaskBuilder().withPeople(List.of()).build();
        sampleTasks.addTask(noContactsAssigned);

        Index index = Index.fromOneBased(1);

        Model model = new ModelManager(new AddressBook(), new UserPrefs(), sampleTasks);

        GenerateEmailsCommand command = new GenerateEmailsCommand(index);

        String expected = String.format(MESSAGE_NO_CONTACTS_ASSIGNED, noContactsAssigned);
        String actual = command.execute(model).toString();

        assertEquals(expected, actual);
    }

    @Test
    void equals_sameAssignedIndex_success() {
        GenerateEmailsCommand c1 = new GenerateEmailsCommand(Index.fromOneBased(1));
        GenerateEmailsCommand c2 = new GenerateEmailsCommand(Index.fromOneBased(1));

        assertEquals(c1, c2);
    }
}
