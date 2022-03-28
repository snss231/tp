package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

public class GenerateEmailsCommand extends Command {

    public static final String COMMAND_WORD = "gen";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Generates all the emails of the people related to the task "
            + "identified by the index number in the task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_NO_CONTACTS_ASSIGNED =
            "Failed: There are no contacts assigned to the task %1$s";

    private static final String MESSAGE_GENERATED_EMAILS = "Here are the emails related to the task %1$s:\n"
            + "%2$s";


    private final Index targetIndex;

    public GenerateEmailsCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> shownTaskList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= shownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task task = shownTaskList.get(targetIndex.getZeroBased());

        if (task.getNoOfPeople() == 0) {
            return new CommandResult(String.format(MESSAGE_NO_CONTACTS_ASSIGNED, task));
        }

        String emails = task.getEmails();

        return new CommandResult(String.format(MESSAGE_GENERATED_EMAILS, task, emails), emails);
    }
}
