package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainInTask;
import seedu.address.model.task.Task;

/**
 * Finds and lists all persons in address book who were assigned to a given task.
 */
public class ViewTaskCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display all relevant contacts under a task "
            + "by the index number used in the displayed task list. \n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String NO_CONTACT_ASSIGN = "There were no contact assigned to this task.";
    public static final String DISPLAY_TASK_CONTACT_SUCCESS = "There were %1$d contacts assigned to this task";

    private final Index targetIndex;

    /**
     * The constructor for ViewTaskCommand class
     *
     * @param targetIndex The index of the task to be targeted.
     */
    public ViewTaskCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDisplay = lastShownTaskList.get(targetIndex.getZeroBased());

        List<Person> listOfPeople = taskToDisplay.getPeople();

        PersonContainInTask predicate = new PersonContainInTask(listOfPeople);

        model.updateFilteredPersonList(predicate);

        if (model.getFilteredPersonList().size() < 1) {
            return new CommandResult(NO_CONTACT_ASSIGN);

        }

        return new CommandResult(
                String.format(DISPLAY_TASK_CONTACT_SUCCESS, model.getFilteredPersonList().size()));
    }
}
