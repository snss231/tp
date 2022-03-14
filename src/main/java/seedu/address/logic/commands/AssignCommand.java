package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;


public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add the person identified by the index number "
            + "used in the displayed person list "
            + "to the task identified by the index number used in the displayed task list.\n"
            + "Parameters: TASK_INDEX [" + PREFIX_PERSON + "PERSON_INDEX]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_PERSON + "2";

    public static final String MESSAGE_ADD_PERSON_TO_TASK_SUCCESS = "Added %1$s to the task %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "Failed: The person %1$s is already assigned to the task $2$s";


    private final Index taskIndex;
    private final Index personIndex;

    /**
     * @param taskIndex of the task in the filtered task list to be added to
     * @param personIndex of the person in the filtered person list to add
     */
    public AssignCommand(Index taskIndex, Index personIndex) {
        requireNonNull(taskIndex);
        requireNonNull(personIndex);

        this.personIndex = personIndex;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> shownPersonList = model.getFilteredPersonList();
        List<Task> shownTaskList = model.getFilteredTaskList();

        if (personIndex.getZeroBased() >= shownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (taskIndex.getZeroBased() >= shownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task taskToEdit = shownTaskList.get(taskIndex.getZeroBased());
        Person personToAdd = shownPersonList.get(personIndex.getZeroBased());

        if (taskToEdit.getPeople().contains(personToAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON, personToAdd, taskToEdit));
        }
        Task updatedTask = getUpdatedTask(personToAdd, taskToEdit);

        model.setTask(taskToEdit, updatedTask);
        return new CommandResult(String.format(MESSAGE_ADD_PERSON_TO_TASK_SUCCESS, personToAdd, updatedTask));
    }

    private Task getUpdatedTask(Person personToAdd, Task taskToUpdate) {
        List<Person> updatedList = new ArrayList<>(taskToUpdate.getPeople());
        updatedList.add(personToAdd);
        Task editedTask = new Task(taskToUpdate.getName(), taskToUpdate.getDateTime(), updatedList);
        return editedTask;
    }
}
