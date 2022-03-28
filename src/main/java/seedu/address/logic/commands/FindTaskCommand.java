package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskBetweenDatesPredicate;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;



/**
 * Finds and lists all tasks in task storage whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "findt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " apple orange pear";

    public static final String ERROR_MESSAGE_INVALID_FORMAT =
            "Invalid date format. It should be \"dd-mm-yyyy HHMM\"";
    public static final String ERROR_MESSAGE_INVALID_TAG =
            "Invalid tag format. It should be \"d/dd-mm-yyyy HHMM, dd-mm-yyyy HHMM\"";


    private final Predicate<? extends Task> predicate;

    public FindTaskCommand(TaskNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public FilterCommand(TaskBetweenDatesPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Give reason why it safe to suppress warning here.
     */
    @SuppressWarnings("unchecked")
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredTaskList((Predicate<Task>) predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && predicate.equals(((FindTaskCommand) other).predicate)); // state check
    }
}
