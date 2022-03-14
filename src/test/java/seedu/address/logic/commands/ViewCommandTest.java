package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalAssignedTasks.getTypicalAssignedTaskList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainInTask;
import seedu.address.model.task.Task;

public class ViewCommandTest {

    // Model with no people assigned to the tasks
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList());

    // Model with people assigned to the tasks
    private Model assignedModel =
            new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAssignedTaskList());
    private Model expectedAssignedModel =
            new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAssignedTaskList());

    @Test
    void execute_zeroAssigneesInTask_noPersonFound() throws CommandException, ParseException {
        Index targetIndex = ParserUtil.parseIndex("1");

        // Initializing command
        ViewCommand command = new ViewCommand(targetIndex);

        // Initializing predicate for expected model
        Task targetTask = expectedModel.getFilteredTaskList().get(targetIndex.getZeroBased());
        List<Person> listOfPeople = targetTask.getPeople();
        PersonContainInTask predicate = preparePredicate(listOfPeople);

        // Get expected result from model
        expectedModel.updateFilteredPersonList(predicate);
        // Get result from command execute
        command.execute(model);

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }


    @Test
    void execute_assigneesInTask_personFound() throws CommandException, ParseException {
        String expectedMessage = String.format(ViewCommand.DISPLAY_TASK_CONTACT_SUCCESS, 2);
        Index targetIndex = ParserUtil.parseIndex("1");

        // Initializing command
        ViewCommand command = new ViewCommand(targetIndex);

        // Initializing predicate for expected model
        Task targetTask = expectedAssignedModel.getFilteredTaskList().get(targetIndex.getZeroBased());
        List<Person> listOfPeople = targetTask.getPeople();
        PersonContainInTask predicate = preparePredicate(listOfPeople);

        // Get expected result from model
        expectedAssignedModel.updateFilteredPersonList(predicate);
        // Get result from command execute
        command.execute(assignedModel);

        assertEquals(expectedAssignedModel.getFilteredPersonList(), assignedModel.getFilteredPersonList());
    }

    private PersonContainInTask preparePredicate(List<Person> personList) {
        return new PersonContainInTask(personList);
    }
}
