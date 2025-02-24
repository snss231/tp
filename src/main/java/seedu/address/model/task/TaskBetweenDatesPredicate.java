package seedu.address.model.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class TaskBetweenDatesPredicate implements Predicate<Task> {
    private final List<LocalDateTime> beforeAfterDates;

    public TaskBetweenDatesPredicate(List<LocalDateTime> beforeAfterDates) {
        this.beforeAfterDates = beforeAfterDates;
    }

    @Override
    public boolean test(Task task) {
        if (task.getEndDateTime() == null) {
            return (task.getDateTime().isAfter(beforeAfterDates.get(0))
                    && task.getDateTime().isBefore(beforeAfterDates.get(1))
                    || task.getDateTime().isEqual(beforeAfterDates.get(0))
                    || task.getDateTime().isEqual(beforeAfterDates.get(1)));
        } else {
            return (task.getDateTime().isAfter(beforeAfterDates.get(0))
                    && task.getDateTime().isBefore(beforeAfterDates.get(1))
                    || (task.getEndDateTime().isAfter(beforeAfterDates.get(0))
                    && task.getEndDateTime().isBefore(beforeAfterDates.get(1)))
                    || task.getDateTime().isEqual(beforeAfterDates.get(0))
                    || task.getEndDateTime().isEqual(beforeAfterDates.get(1)));
        }


    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskBetweenDatesPredicate // instanceof handles nulls
                && beforeAfterDates.equals(((TaskBetweenDatesPredicate) other).beforeAfterDates)); // state check
    }

}
