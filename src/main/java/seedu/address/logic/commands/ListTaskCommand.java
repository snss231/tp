package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_ALL_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_COMPLETE_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST_INCOMPLETE_TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MARK_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_UNMARK_TASKS;

import seedu.address.model.Model;

/**
 * Lists all tasks in the address book to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "listt";

    public static final String MESSAGE_SUCCESS_ALL = "Listed all tasks";

    public static final String MESSAGE_SUCCESS_COMPLETED = "Listed all completed task(s)";

    public static final String MESSAGE_SUCCESS_NOT_COMPLETED = "Listed all incomplete task(s)";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List tasks\n"
            + "Parameters: [" + PREFIX_LIST_ALL_TASK + "] for all (complete + incomplete) tasks or\n "
            + "[" + PREFIX_LIST_INCOMPLETE_TASK + "] for incomplete tasks or\n "
            + "[" + PREFIX_LIST_COMPLETE_TASK + "] for complete tasks\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_LIST_ALL_TASK;

    private String prefix;

    /**
     * Constructor for ListTaskCommand
     *
     * @param prefix prefix based on user input.
     */
    public ListTaskCommand(String prefix) {
        requireNonNull(prefix);
        this.prefix = prefix;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (prefix.equals("all")) {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(MESSAGE_SUCCESS_ALL);
        } else if (prefix.equals("c")) {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_MARK_TASKS);
            return new CommandResult(MESSAGE_SUCCESS_COMPLETED);
        } else {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_UNMARK_TASKS);
            return new CommandResult(MESSAGE_SUCCESS_NOT_COMPLETED);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTaskCommand // instanceof handles nulls
                && prefix.equals(((ListTaskCommand) other).prefix)); // state check
    }
}
