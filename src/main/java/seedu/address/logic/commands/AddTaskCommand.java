package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AddTaskCommand extends Command {

    public static final String MESSAGE_USAGE = "add task" + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_TASKNAME + "TASKNAME "
            + PREFIX_DATETIME + "DATETIME "
            + "Example: " + "add task" + " "
            + PREFIX_TASKNAME + "John Doe "
            + PREFIX_DATETIME + "2022-12-25 1800";

    private final String taskName;
    private final LocalDateTime dateTime;

    public static final String COMMAND_WORD = "add task";
    public static final String ADD_TASK_SUCCESS = "Task added!";

    public AddTaskCommand(String taskName, LocalDateTime dateTime) {
        requireNonNull(taskName);
        requireNonNull(dateTime);

        this.taskName = taskName;
        this.dateTime = dateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(ADD_TASK_SUCCESS);
    }
}
