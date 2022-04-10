package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskBetweenDatesPredicate;
import seedu.address.testutil.TypicalLocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalLocalDateTime.DATE_1;
import static seedu.address.testutil.TypicalLocalDateTime.DATE_2;
import static seedu.address.testutil.TypicalLocalDateTime.DATE_3;
import static seedu.address.testutil.TypicalLocalDateTime.DATE_4;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.*;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterByDateCommand.
 */
public class FilterByDateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),  getTypicalTaskList());

    @Test
    public void equals() {
        TaskBetweenDatesPredicate firstPredicate =
                new TaskBetweenDatesPredicate(new ArrayList<>(Arrays.asList(DATE_1, DATE_2)));
        TaskBetweenDatesPredicate secondPredicate =
                new TaskBetweenDatesPredicate(new ArrayList<>(Arrays.asList(DATE_3, DATE_4)));

        FilterByDateCommand firstFilterDateCommand = new FilterByDateCommand(firstPredicate);
        FilterByDateCommand secondFilterDateCommand = new FilterByDateCommand(secondPredicate);

        // same object  -> returns true
        assertTrue(firstFilterDateCommand.equals(firstFilterDateCommand));

        // same values -> return true
        FilterByDateCommand firstFilterDateCommandCopy = new FilterByDateCommand(firstPredicate);
        assertTrue(firstFilterDateCommand.equals(firstFilterDateCommandCopy));

        // different types -> returns false
        assertFalse(firstFilterDateCommand.equals(1));

        // null -> returns false
        assertFalse(firstFilterDateCommand.equals(null));

        // different task -> returns false
        assertFalse(firstFilterDateCommand.equals(secondFilterDateCommand));

    }

    /**
     * Date Range: 10 Mar -> 6 Jul
     * Task Filtered: Laundry - 2 June
     */
    @Test
    public void execute_validDateTimeRange_oneTaskFound() {
        String expectedMessage = String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        TaskBetweenDatesPredicate predicate = new TaskBetweenDatesPredicate(TypicalLocalDateTime.getTypicalDateTimes());
        FilterByDateCommand command = new FilterByDateCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CONSULTATION), model.getFilteredTaskList());
    }

    /**
     * Date Range: 14 Jan-> 10 Mar
     * Task Filtered: None
     */
    @Test
    public void execute_validDateTimeRange_noTaskFound() {
        String expectedMessage = String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskBetweenDatesPredicate predicate = new TaskBetweenDatesPredicate(
                new ArrayList<>(Arrays.asList(DATE_4, DATE_1)));
        FilterByDateCommand command = new FilterByDateCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }


}
