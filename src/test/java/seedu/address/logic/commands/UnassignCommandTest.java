package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
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

class UnassignCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList());

    @Test
    void execute_unassignExistingContact_success() {
        Person person = model.getFilteredPersonList().get(0);
        Task taskToEdit = model.getFilteredTaskList().get(0);
        taskToEdit.addPerson(person);

        Task updatedTask = new Task(taskToEdit.getName(), taskToEdit.getDateTime(), taskToEdit.getPeople());
        updatedTask.addPerson(person);
        TaskList updatedTasks = new TaskList(model.getTaskList());
        updatedTasks.setTask(taskToEdit, updatedTask);

        UnassignCommand unassignCommand =
                new UnassignCommand(Index.fromZeroBased(0), Index.fromZeroBased(0));

        Model m = new ModelManager(
                new AddressBook(model.getAddressBook()), new UserPrefs(), updatedTasks);

        String expectedMessage = String.format(
                UnassignCommand.MESSAGE_REMOVE_PERSON_FROM_TASK_SUCCESS, person, updatedTask);
        assertCommandSuccess(unassignCommand, m, expectedMessage, model);
    }

    @Test
    void execute_unassignNonexistingContact_failure() {
        Person personToRemove = model.getFilteredPersonList().get(0);
        Task taskToEdit = model.getFilteredTaskList().get(0);
        taskToEdit.removePerson(personToRemove);

        assertFalse(taskToEdit.getPeople().contains(personToRemove));

        UnassignCommand unassignCommand =
                new UnassignCommand(Index.fromZeroBased(0), Index.fromZeroBased(0));

        assertCommandFailure(unassignCommand, model, UnassignCommand.MESSAGE_PERSON_NOT_IN_TASK);
    }
}
