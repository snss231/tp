package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {
    private static final String dateTimePattern = "dd-MM-yyyy HHmm";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);

    /*
     * Returns an add command string for adding the {@code task}.
     */
    public static String getAddCommand(Task task) {
        return AddTaskCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TASKNAME + task.getName() + " ");
        sb.append(PREFIX_DATETIME + formatLocalDateTime(task.getDateTime()) + " ");
        sb.append(PREFIX_LINK + task.getLink().toString() + " ");
        task.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditTaskDescriptor}'s details.
     */
    public static String getEditTaskDescriptorDetails(EditTaskDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_TASKNAME).append(name).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATETIME)
                .append(formatLocalDateTime(date)).append(" "));
        descriptor.getLink().ifPresent(link -> sb.append(PREFIX_LINK).append(link).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }

    private static String formatLocalDateTime(LocalDateTime toBeFormatted) {
        return toBeFormatted.format(formatter);
    }
}
