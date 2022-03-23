package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;

import java.time.LocalDateTime;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Link;
import seedu.address.model.task.Task;

/**
 * Represents an AddTaskCommand.
 */
public class AddTaskCommand extends Command {

    /* Message printed if wrong usage */
    public static final String MESSAGE_USAGE = "addt" + ": Adds a task to the NUS Classes. "
            + "Parameters: "
            + PREFIX_TASKNAME + "TASKNAME "
            + PREFIX_DATETIME + "DATETIME "
            + PREFIX_TAG + "TAG "
            + PREFIX_LINK + "LINK "
            + PREFIX_RECURRING + "PERIOD RECURRENCE\n"
            + "Example: " + "addt" + " "
            + PREFIX_TASKNAME + "John Doe "
            + PREFIX_DATETIME + "25-12-2022 1800 "
            + PREFIX_TAG + "CS2103T"
            + " [" + PREFIX_LINK + "https://...]"
            + " [" + PREFIX_RECURRING + "5 5]\n"
            + "Hint: for " + PREFIX_RECURRING + " you can use predefined values [annually, quarterly, monthly,"
            + " weekly, daily] for the period field.";

    public static final String COMMAND_WORD = "addt";
    public static final String ADD_TASK_SUCCESS = "Task added!";

    private final String taskName;
    private final LocalDateTime dateTime;
    private final Set<Tag> tags;
    private final Link link;
    private final int recurrence;
    private final int period;

    /**
     * Constructor for AddTaskCommand. Takes in 4 parameters, taskName, dateTime, tags
     * and link. There can be multiple tags.
     *
     * @param taskName Name of Task.
     * @param dateTime LocalDateTime object to represent date time of Task.
     * @param tags A set of tags link to the Task.
     * @param link Link of a task.
     */
    public AddTaskCommand(String taskName, LocalDateTime dateTime, Set<Tag> tags, Link link) {
        requireAllNonNull(taskName, dateTime, tags, link);

        this.taskName = taskName;
        this.dateTime = dateTime;
        this.tags = tags;
        this.link = link;
        this.recurrence = 0;
        this.period = 0;
    }

    /**
     * Constructor for AddTaskCommand. Takes in 6 parameters, taskName, dateTime, tags, 
     * link, recurrence, and period. There can be multiple tags.
     *
     * @param taskName Name of Task.
     * @param dateTime LocalDateTime object to represent date time of Task.
     * @param tags A set of tags link to the Task.
     * @param link Link of a task.
     * @param recurrence The number of times the task should recur.
     * @param period The number of days apart each task should be.
     */
    public AddTaskCommand(String taskName, LocalDateTime dateTime, Set<Tag> tags, Link link,
                          int recurrence, int period) {

        requireAllNonNull(taskName, dateTime, tags, link);
        this.taskName = taskName;
        this.dateTime = dateTime;
        this.tags = tags;
        this.link = link;
        this.recurrence = recurrence;
        this.period = period;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Task taskToBeAdded = new Task(taskName, dateTime, tags, link);
        model.getTaskList().addTask(taskToBeAdded);
        for (int i = 1; i < period; i++) {
            LocalDateTime temp = dateTime.plusDays(i * recurrence);
            taskToBeAdded = new Task(taskName, temp, tags, link);
            model.getTaskList().addTask(taskToBeAdded);
        }
        return new CommandResult(ADD_TASK_SUCCESS);
    }
}
