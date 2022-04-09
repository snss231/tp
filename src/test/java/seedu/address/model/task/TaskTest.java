package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
