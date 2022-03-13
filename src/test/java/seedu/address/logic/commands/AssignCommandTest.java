package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

class AssignCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList());

    @Test
    void execute_addFirstPersonToFirstTask_success() {
        Person personToAdd = model.getFilteredPersonList().get(0);
        Task taskToEdit = model.getFilteredTaskList().get(0);
        Task updatedTask = new Task(taskToEdit.getName(), taskToEdit.getDateTime(), taskToEdit.getPeople());
        updatedTask.addPerson(personToAdd);

        AssignCommand assignCommand =
                new AssignCommand(Index.fromZeroBased(0), Index.fromZeroBased(0));

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), new TaskList(model.getTaskList()));
        expectedModel.setTask(taskToEdit, updatedTask);

        String expectedMessage = String.format(
                AssignCommand.MESSAGE_ADD_PERSON_TO_TASK_SUCCESS, personToAdd, updatedTask);
        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_addDuplicatePersonToTask_failure() {
        Person personToAdd = model.getFilteredPersonList().get(0);
        Task taskToEdit = model.getFilteredTaskList().get(0);
        Task updatedTask = new Task(taskToEdit.getName(), taskToEdit.getDateTime(), taskToEdit.getPeople());
        updatedTask.addPerson(personToAdd);

        AssignCommand assignCommand =
                new AssignCommand(Index.fromZeroBased(0), Index.fromZeroBased(0));

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), new TaskList(model.getTaskList()));
        expectedModel.setTask(taskToEdit, updatedTask);

        String expectedMessage = String.format(
                AssignCommand.MESSAGE_ADD_PERSON_TO_TASK_SUCCESS, personToAdd, updatedTask);
        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }
}
