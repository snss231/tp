package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskList;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

public class TypicalAssignedTasks {
    public static final List<Person> GROUP_ONE = Arrays.asList(TypicalPersons.AMY, TypicalPersons.ALICE);
    public static final List<Person> GROUP_TWO =
            Arrays.asList(TypicalPersons.CARL, TypicalPersons.DANIEL, TypicalPersons.BOB);
    public static final List<Person> GROUP_THREE = Arrays.asList(TypicalPersons.GEORGE);

    public static final Task BRUSH_TEETH = new TaskBuilder().withTaskName("Brush my teeth")
            .withDateTime(LocalDateTime.of(2022, 12, 15, 21, 0))
            .withPeople(GROUP_ONE)
            .withTags("Chores").withLink("").build();

    public static final Task LAUNDRY = new TaskBuilder().withTaskName("Do the laundry")
            .withDateTime(LocalDateTime.of(2022, 6, 2, 15, 0))
            .withPeople(GROUP_TWO)
            .withTags("Chores").withLink("").build();

    public static final Task CONSULTATION = new TaskBuilder().withTaskName("Consultation with students")
            .withDateTime(LocalDateTime.of(2022, 8, 3, 14, 0))
            .withTags("Consult")
            .withPeople(GROUP_THREE)
            .withLink("https://nus-sg.zoom.us/j/86344685271?pwd=Uk5JZUJiRktJbURydHpGVXRNd0lPUT09#success")
            .build();

    public static final Task INVIGILATOR_MEETING = new TaskBuilder().withTaskName("Meeting with exam invigilators")
            .withDateTime(LocalDateTime.of(2022, 2, 5, 14, 30))
            .withTags("Meeting")
            .withLink("https://nus-sg.zoom.us/j/86344685271?pwd=Uk5JZUJiRktJbURydHpGVXRNd0lPUT09#success")
            .build();

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
