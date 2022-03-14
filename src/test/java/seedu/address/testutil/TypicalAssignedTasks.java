package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskList;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Adapted from {}
 */
public class TypicalAssignedTasks {
    public static final List<Person> GROUP_ONE = Arrays.asList(TypicalPersons.AMY, TypicalPersons.ALICE);
    public static final List<Person> GROUP_TWO =
            Arrays.asList(TypicalPersons.CARL, TypicalPersons.DANIEL, TypicalPersons.BOB);
    public static final List<Person> GROUP_THREE = Arrays.asList(TypicalPersons.GEORGE);

    public static final Task BRUSH_TEETH = new Task(
            "Brush my teeth", LocalDateTime.of(2022, 12, 15, 21, 0), GROUP_ONE);
    public static final Task LAUNDRY = new Task(
            "Do the laundry", LocalDateTime.of(2022, 6, 2, 15, 0), GROUP_TWO);
    public static final Task CONSULTATION = new Task(
            "Consultation with students",
            LocalDateTime.of(2022, 8, 3, 14, 0), GROUP_THREE);
    public static final Task INVIGILATOR_MEETING = new Task(
            "Meeting with exam invigilators",
            LocalDateTime.of(2022, 2, 5, 14, 30));

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(BRUSH_TEETH, LAUNDRY, CONSULTATION, INVIGILATOR_MEETING));
    }

    /**
     * Returns a {@code TaskList} with all the typical tasks with people assigned.
     */
    public static TaskList getTypicalAssignedTaskList() {
        TaskList tl = new TaskList();
        for (Task task : getTypicalTasks()) {
            tl.addTask(task);
        }
        return tl;
    }
}
