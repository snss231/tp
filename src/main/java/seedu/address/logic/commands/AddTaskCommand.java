package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;

import java.time.LocalDateTime;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Represents an AddTaskCommand.
 */
public class AddTaskCommand extends Command {

    /* Message printed if wrong usage */
    public static final String MESSAGE_USAGE = "add task" + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_TASKNAME + "TASKNAME "
            + PREFIX_DATETIME + "DATETIME "
            + "Example: " + "add task" + " "
            + PREFIX_TASKNAME + "John Doe "
            + PREFIX_DATETIME + "2022-12-25 1800";

    public static final String COMMAND_WORD = "addt";
    public static final String ADD_TASK_SUCCESS = "Task added!";

    private final String taskName;
    private final LocalDateTime dateTime;



    /**
     * Constructor for AddTaskCommand. Takes in 2 parameters, taskName and dateTime
     *
     * @param taskName Name of Task
     * @param dateTime LocalDateTime object to represent date time of Task
     */
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
