package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_TASKB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LINK_TASKB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TASKB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TASKB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.BRUSH_TEETH;
import static seedu.address.testutil.TypicalTasks.CONSULTATION;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {
    @Test
    void hasInvalidDateRange_validRange_success() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);

        Task task = new TaskBuilder().withDateTime(today).withEndDateTime(tomorrow).build();

        assertFalse(task.hasInvalidDateRange());
    }

    @Test
    void hasInvalidDateRange_invalidDate_failure() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);

        Task task = new TaskBuilder().withDateTime(tomorrow).withEndDateTime(today).build();

        assertTrue(task.hasInvalidDateRange());
    }

    @Test
    void hasInvalidDateRange_sameDateTimeTime_failure() {
        LocalDateTime today = LocalDateTime.now();

        Task task = new TaskBuilder().withDateTime(today).withEndDateTime(today).build();

        assertTrue(task.hasInvalidDateRange());
    }

    @Test
    void hasInvalidDateRange_endBeforeStart_failure() {
        LocalDateTime today = LocalDateTime.now().withHour(2).withMinute(16);
        LocalDateTime oneHourAgo = today.withMinute(15);

        Task task = new TaskBuilder().withDateTime(today).withEndDateTime(oneHourAgo).build();

        assertTrue(task.hasInvalidDateRange());
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(CONSULTATION.isSameTask(CONSULTATION));

        // null -> returns false
        assertFalse(CONSULTATION.isSameTask(null));
    }

    @Test
    public void equals() {
        Task consultationCopy = new TaskBuilder(CONSULTATION).build();
        assertTrue(CONSULTATION.equals(consultationCopy));

        assertTrue(CONSULTATION.equals(CONSULTATION));

        assertFalse(CONSULTATION.equals(null));

        assertFalse(CONSULTATION.equals(BRUSH_TEETH));

        // different name -> returns false
        Task editedConsultation = new TaskBuilder(CONSULTATION).withTaskName(VALID_NAME_TASKB).build();
        assertFalse(editedConsultation.getName().equals(CONSULTATION.getName()));

        // different link -> returns false
        editedConsultation = new TaskBuilder(CONSULTATION).withLink(VALID_LINK_TASKB).build();
        assertFalse(editedConsultation.getLink().equals(CONSULTATION.getLink()));

        // different date -> returns false
        editedConsultation = new TaskBuilder(CONSULTATION).withDateTime(VALID_DATETIME_TASKB).build();
        assertFalse(editedConsultation.getDateTime().equals(CONSULTATION.getDateTime()));

        // different tags -> return false
        editedConsultation = new TaskBuilder(CONSULTATION).withTags(VALID_TAG_TASKB).build();
        assertFalse(editedConsultation.getTags().equals(CONSULTATION.getTags()));
    }
}
