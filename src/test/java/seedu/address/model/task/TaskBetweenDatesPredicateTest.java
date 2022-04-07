package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;


public class TaskBetweenDatesPredicateTest {
    private final LocalDateTime beforeDateTime = LocalDateTime.of(2050, 6, 6, 6, 0);
    private final LocalDateTime afterDateTime = LocalDateTime.of(2050, 12, 12, 12, 0);
    private ArrayList<LocalDateTime> list = new ArrayList<>();

    @Test
    public void testValidDate() {
        list.add(beforeDateTime);
        list.add(afterDateTime);
        assertEquals(list.size(), 2);
        TaskBetweenDatesPredicate predicate = new TaskBetweenDatesPredicate(list);

        //In between
        LocalDateTime validDateTime1 = LocalDateTime.of(2050, 9, 9, 9, 0);
        Task taskValidDateTime1 = new TaskBuilder().withDateTime(validDateTime1).build();
        assertTrue(predicate.test(taskValidDateTime1));

        //At the start
        LocalDateTime validDateTime2 = LocalDateTime.of(2050, 6, 6, 6, 1);
        Task taskValidDateTime2 = new TaskBuilder().withDateTime(validDateTime2).build();
        assertTrue(predicate.test(taskValidDateTime2));

        //At the end
        LocalDateTime validDateTime3 = LocalDateTime.of(2050, 12, 12, 11, 59);
        Task taskValidDateTime3 = new TaskBuilder().withDateTime(validDateTime3).build();
        assertTrue(predicate.test(taskValidDateTime3));
    }

    @Test
    public void testInvalidDate() {
        list.add(beforeDateTime);
        list.add(afterDateTime);
        assertEquals(list.size(), 2);
        TaskBetweenDatesPredicate predicate = new TaskBetweenDatesPredicate(list);

        //before
        LocalDateTime invalidDateTime1 = LocalDateTime.of(2050, 6, 6, 5, 59);
        Task invalidTask1 = new TaskBuilder().withDateTime(invalidDateTime1).build();
        assertFalse(predicate.test(invalidTask1));

        //after
        LocalDateTime invalidDateTime2 = LocalDateTime.of(2050, 12, 12, 12, 1);
        Task invalidTask2 = new TaskBuilder().withDateTime(invalidDateTime2).build();
        assertFalse(predicate.test(invalidTask2));
    }

}
