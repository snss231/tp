package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignedTasks.getTypicalAssignedTaskList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.List;

import org.junit.jupiter.api.Test;

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

        String expectedMessage = ViewCommand.NO_CONTACT_ASSIGN;

        ViewCommand viewCommand = new ViewCommand(Index.fromZeroBased(0));

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }


    @Test
    void execute_assigneesInTask_personFound() throws CommandException {
        Model expectedModel = new ModelManager(
                assignedModel.getAddressBook(), new UserPrefs(), new TaskList(assignedModel.getTaskList()));

        String expectedMessage = String.format(ViewCommand.DISPLAY_TASK_CONTACT_SUCCESS, 2);
        List<Person> assignedList = expectedModel.getFilteredTaskList().get(0).getPeople();
        PersonContainInTask pred = new PersonContainInTask(assignedList);

        ViewCommand viewCommand = new ViewCommand(Index.fromZeroBased(0));

        viewCommand.execute(assignedModel);

        assertEquals(assignedModel.getFilteredPersonList(), expectedModel.getFilteredPersonList().filtered(pred));

    }

    private PersonContainInTask preparePredicate(List<Person> personList) {
        return new PersonContainInTask(personList);
    }
}
