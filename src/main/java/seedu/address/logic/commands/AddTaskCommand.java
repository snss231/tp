package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_RANGE;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;

import java.time.LocalDateTime;
import java.util.Set;

import seedu.address.commons.util.TagUtil;
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
    public static final String COMMAND_WORD = "addt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to NUS Classes.\n"
            + "Usage: "
            + COMMAND_WORD + " "
            + PREFIX_TASKNAME + "TASKNAME "
            + PREFIX_DATETIME + "DATETIME [, ENDDATETIME]"
            + " [" + PREFIX_TAG + "TAG] "
            + " [" + PREFIX_LINK + "LINK] "
            + " [" + PREFIX_RECURRING + "PERIOD RECURRENCE]\n"
            + "Example: " + "addt" + " "
            + PREFIX_TASKNAME + "Lecture "
            + PREFIX_DATETIME + "25-12-2023 1800,"
            + "25-12-2023 2000 "
            + PREFIX_TAG + "CS2103T "
            + PREFIX_LINK + "https://... "
            + PREFIX_RECURRING + "5 5\n"
            + "Hint: for " + PREFIX_RECURRING + " you can use predefined values [annually, quarterly, monthly,"
            + " weekly, daily] for the period field.";

    public static final String ADD_TASK_SUCCESS = "Task added!";

    private final String taskName;
    private final LocalDateTime dateTime;
    private final LocalDateTime endDateTime;
    private final Set<Tag> tags;
    private final Link link;
    private final int recurrence;
    private final int period;
    private final boolean isTaskMarkDone;



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
    public AddTaskCommand(String taskName, LocalDateTime dateTime, LocalDateTime endDateTime, Set<Tag> tags, Link link,
                          int recurrence, int period) {

        requireAllNonNull(taskName, dateTime, tags);
        this.taskName = taskName;
        this.dateTime = dateTime;
        this.endDateTime = endDateTime;
        this.tags = tags;
        this.link = link;
        this.recurrence = recurrence;
        this.period = period;
        this.isTaskMarkDone = false;
    }

    /**
     * Constructor for AddTaskCommand without period or recurrence.
     */
    public AddTaskCommand(String taskName, LocalDateTime dateTime, LocalDateTime endDateTime, Set<Tag> tags,
                          Link link) {
        this(taskName, dateTime, endDateTime, tags, link, 0, 0);
    }

    /**
     * Constructor that takes in a Task and creates an AddTaskCommand.
     *
     * @param task Task from which information is added to AddTaskCommand.
     */
    public AddTaskCommand(Task task) {
        this.taskName = task.getName();
        this.dateTime = task.getDateTime();
        this.endDateTime = task.getEndDateTime();
        this.tags = task.getTags();
        this.link = task.getLink();
        this.recurrence = 0;
        this.period = 0;
        this.isTaskMarkDone = false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        String checkTagLength = TagUtil.checkTagLength(tags);

        //null value represents no tags are too long.
        if (checkTagLength != null) {
            throw new CommandException(checkTagLength);
        }
        Task taskToBeAdded = new Task(taskName, dateTime, endDateTime, tags, link, isTaskMarkDone);

        if (taskToBeAdded.hasInvalidDateRange()) {
            throw new CommandException(MESSAGE_INVALID_DATE_RANGE);
        }
        model.addTask(taskToBeAdded);
        for (int i = 1; i < period; i++) {
            LocalDateTime temp = dateTime.plusDays(i * recurrence);
            LocalDateTime tempEnd = null;
            if (!isNull(endDateTime)) {
                tempEnd = endDateTime.plusDays(i * recurrence);
            }
            taskToBeAdded = new Task(taskName, temp, tempEnd, tags, link, isTaskMarkDone);
            model.addTask(taskToBeAdded);
        }
        return new CommandResult(ADD_TASK_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof AddTaskCommand
                && this.taskName.equals(((AddTaskCommand) other).taskName)
                && this.dateTime.equals(((AddTaskCommand) other).dateTime)
                && this.tags.equals(((AddTaskCommand) other).tags)
                && this.link.equals(((AddTaskCommand) other).link));
    }

    @Override
    public String toString() {
        return this.taskName + " " + this.dateTime + " " + this.tags + " " + this.link;
    }
}
