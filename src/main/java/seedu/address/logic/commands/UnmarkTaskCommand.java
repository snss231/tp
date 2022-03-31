package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Unmarks a task identified using it's displayed index as not complete.
 */
public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the task identified by the index number used in the displayed task list.\n"
            + "Index must be a positive integer."
            + "Usage: "
            + COMMAND_WORD + " "
            + "INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Unmarked Task: %1$s";

    private final Index targetIndex;


    /**
     * Constructor for UnmarkTaskCommand.
     *
     * @param targetIndex the index of the task.
     */
    public UnmarkTaskCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownList.get(targetIndex.getZeroBased());
        model.unmarkTask(taskToMark);
        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, taskToMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkTaskCommand // instanceof handles nulls
                && targetIndex.equals(((UnmarkTaskCommand) other).targetIndex)); // state check
    }
}
