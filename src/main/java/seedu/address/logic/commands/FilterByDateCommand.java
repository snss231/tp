package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.TaskBetweenDatesPredicate;



/**
 * Finds and lists all tasks in task storage whose date falls in between two given dates.
 * Date format: dd-MM-yyyy HHmm
 */
public class FilterByDateCommand extends Command {

    public static final String COMMAND_WORD = "findt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose dates fall within "
            + "the specified dates inputs (order insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: dt/DATETIME1, DATETIME2\n"
            + "Example: " + COMMAND_WORD + " dt/12-01-2022 0900, 13-02-2022 0900";

    private final TaskBetweenDatesPredicate predicate;

    /**
     * Constructor for FilterByDateCommand
     *
     * @param predicate Predicate that returns true if task's date falls on or in between the given range
     */
    public FilterByDateCommand(TaskBetweenDatesPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterByDateCommand // instanceof handles nulls
                && predicate.equals(((FilterByDateCommand) other).predicate)); // state check
    }
}
