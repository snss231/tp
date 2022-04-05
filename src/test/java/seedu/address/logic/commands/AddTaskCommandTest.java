package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_RANGE;
import static seedu.address.commons.core.Messages.MESSAGE_TAG_TOO_LONG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TaskBuilder.DEFAULT_DATETIME;
import static seedu.address.testutil.TaskBuilder.DEFAULT_NAME;
import static seedu.address.testutil.TaskBuilder.DEFAULT_TAG;
import static seedu.address.testutil.TaskBuilder.DEFAULT_ZOOMLINK;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Link;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandTest {

    @Test
    public void constructor_nullTaskAttributes_throwsNullPointerException() {
        ArrayList<Tag> list = new ArrayList<>();
        list.add(new Tag(DEFAULT_TAG));
        Set<Tag> defaultTagSet = new HashSet<Tag>(list);
        Link defaultLink = new Link(DEFAULT_ZOOMLINK);

        //Null Taskname
        assertThrows(NullPointerException.class, ()
            -> new AddTaskCommand(null, DEFAULT_DATETIME, DEFAULT_DATETIME,
                        defaultTagSet, defaultLink));

        //Null datetime
        assertThrows(NullPointerException.class, ()
            -> new AddTaskCommand(DEFAULT_NAME, null, null, defaultTagSet, defaultLink));

        //Null tag set
        assertThrows(NullPointerException.class, ()
            -> new AddTaskCommand(DEFAULT_NAME, DEFAULT_DATETIME, DEFAULT_DATETIME, null, defaultLink));

        //Null link
        assertThrows(NullPointerException.class, ()
            -> new AddTaskCommand(DEFAULT_NAME, DEFAULT_DATETIME, DEFAULT_DATETIME, defaultTagSet, null));
    }

    @Test
    public void execute_taskAcceptedByModel_addSuccessful() throws Exception {
        Model modelAcceptingTask = new ModelManager();
        Task validTask = new TaskBuilder().build();
        CommandResult validResult = new AddTaskCommand(validTask).execute(modelAcceptingTask);

        //Check that task is added
        assertEquals(String.format(AddTaskCommand.ADD_TASK_SUCCESS, validTask), validResult.getFeedbackToUser());

        //Check that TaskList in Model contains validTask
        TaskList validTaskList = new TaskList();
        validTaskList.addTask(validTask);
        assertEquals(validTaskList, modelAcceptingTask.getTaskList());
    }

    @Test
    public void execute_invalidTaskDateTimeThrowsCommandException() throws Exception {
        Model model = new ModelManager();

        //InvalidDateTime
        Task invalidDateTimeTask = new TaskBuilder().build();
        invalidDateTimeTask.changeEndDateTime(DEFAULT_DATETIME.minusDays(1));
        AddTaskCommand invalidDateTimeCommand = new AddTaskCommand(invalidDateTimeTask);
        assertThrows(CommandException.class,
                MESSAGE_INVALID_DATE_RANGE, () -> invalidDateTimeCommand.execute(model));

        //Invalid Tag Length
        Task invalidTagTask = new TaskBuilder().build();
        ArrayList<Tag> list = new ArrayList<>();
        list.add(new Tag("This tag is over 50 characters long..............................."
                + "............................................................................................"));
        Set<Tag> invalidTagSet = new HashSet<Tag>(list);
        invalidTagTask.setTags(invalidTagSet);
        AddTaskCommand invalidTagCommand = new AddTaskCommand(invalidTagTask);
        assertThrows(CommandException.class,
                String.format(MESSAGE_TAG_TOO_LONG,
                        invalidTagSet.stream()
                                .filter(tag -> tag.tagName.length() > 50)
                                .reduce("", (str, tag) -> tag + "\n" + str, (s1, s2) -> s1 + s2)), ()
                -> invalidTagCommand.execute(model));

    }

    @Test
    public void equals() {
        Task validTask = new TaskBuilder().build();
        AddTaskCommand command1 = new AddTaskCommand(validTask);
        AddTaskCommand command2 = new AddTaskCommand(validTask);
        assertTrue(command1.equals(command2));
    }
}
