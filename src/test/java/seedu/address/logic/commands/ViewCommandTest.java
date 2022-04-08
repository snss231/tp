package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignedTasks.getTypicalAssignedTaskList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainInTask;


public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList());

    private Model assignedModel =
            new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAssignedTaskList());

    @Test
    void execute_zeroAssigneesInTask_noPersonFound() {
        Model expectedModel =
                new ModelManager(model.getAddressBook(), new UserPrefs(), new TaskList(model.getTaskList()));

        Index taskIndex = Index.fromZeroBased(0);

        String expectedMessage = String.format(ViewCommand.NO_CONTACT_ASSIGN, taskIndex.getOneBased());

        ViewCommand viewCommand = new ViewCommand(taskIndex);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }


    @Test
    void execute_assigneesInTask_personFound() throws CommandException {
        Model expectedModel = new ModelManager(
                assignedModel.getAddressBook(), new UserPrefs(), new TaskList(assignedModel.getTaskList()));

        List<Person> assignedList = expectedModel.getFilteredTaskList().get(0).getPeople();
        PersonContainInTask pred = new PersonContainInTask(assignedList);

        ViewCommand viewCommand = new ViewCommand(Index.fromZeroBased(0));

        viewCommand.execute(assignedModel);

        assertEquals(assignedModel.getFilteredPersonList(), expectedModel.getFilteredPersonList().filtered(pred));

    }

    @Test
    void execute_userInputIndex_greaterThanList() {
        ViewCommand viewCommand = new ViewCommand(Index.fromZeroBased(100));

        CommandException thrown = Assertions.assertThrows(CommandException.class, ()-> {
            viewCommand.execute(model);
        });
        assertEquals(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, thrown.getMessage());
    }

}
