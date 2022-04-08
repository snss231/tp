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
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display all contacts assigned to the task identified "
            + "by the index number used in the displayed task list. \n"
            + "Usage: "
            + COMMAND_WORD + " "
            + "INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String NO_CONTACT_ASSIGN = "Failed: There are no contacts assigned to task %d.";
    public static final String DISPLAY_TASK_CONTACT_SUCCESS = "Found %1$d contact(s) assigned to this task";

    private final Index targetIndex;

    /**
     * The constructor for ViewTaskCommand class
     *
     * @param targetIndex The index of the task to be targeted.
     */
    public ViewCommand(Index targetIndex) {
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

        int listSize = listOfPeople.size();

        if (listSize < 1) {
            return new CommandResult(String.format(NO_CONTACT_ASSIGN, targetIndex.getOneBased()));
        }

        PersonContainInTask predicate = new PersonContainInTask(listOfPeople);

        model.updateFilteredPersonList(predicate);

        listSize = model.getFilteredPersonList().size();

        return new CommandResult(
                String.format(DISPLAY_TASK_CONTACT_SUCCESS, listSize));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
