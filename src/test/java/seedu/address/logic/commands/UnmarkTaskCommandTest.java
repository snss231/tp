package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

public class UnmarkTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToUnmark = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(UnmarkTaskCommand.MESSAGE_UNMARK_TASK_SUCCESS, taskToUnmark);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getTaskList());
        expectedModel.deleteTask(taskToUnmark);

        assertCommandSuccess(unmarkTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(unmarkTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnmarkTaskCommand unmarkFirstCommand = new UnmarkTaskCommand(INDEX_FIRST_TASK);
        UnmarkTaskCommand unmarkSecondCommand = new UnmarkTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommand));

        // same values -> returns true
        UnmarkTaskCommand unmarkFirstCommandCopy = new UnmarkTaskCommand(INDEX_FIRST_TASK);
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unmarkFirstCommand.equals(unmarkSecondCommand));
    }
}
