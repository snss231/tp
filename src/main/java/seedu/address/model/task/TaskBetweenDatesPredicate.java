package seedu.address.model.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class TaskBetweenDatesPredicate implements Predicate<Task> {
    private final List<LocalDateTime> beforeAfterDateList;

    public TaskBetweenDatesPredicate(List<LocalDateTime> beforeAfterDateList) {
        this.beforeAfterDateList = beforeAfterDateList;
    }

    @Override
    public boolean test(Task task) {
        if (task.getEndDateTime() == null) {
            boolean isTaskDateAfterMinDateRange = task.getDateTime().isAfter(beforeAfterDateList.get(0));
            boolean isTaskDateBeforeMaxDateRange = task.getDateTime().isBefore(beforeAfterDateList.get(1));
            boolean isTaskDateOnMinDateRange = task.getDateTime().isEqual(beforeAfterDateList.get(0));
            boolean isTaskDateOnMaxDateRange = task.getDateTime().isEqual(beforeAfterDateList.get(1));

            boolean isTaskDateValid = isTaskDateAfterMinDateRange && isTaskDateBeforeMaxDateRange;
            return (isTaskDateValid || isTaskDateOnMinDateRange || isTaskDateOnMaxDateRange);

        } else {
            boolean isTaskDateAfterMinDateRange = task.getDateTime().isAfter(beforeAfterDateList.get(0));
            boolean isTaskDateBeforeMaxDateRange = task.getDateTime().isBefore(beforeAfterDateList.get(1));
            boolean isTaskDateOnMinDateRange = task.getDateTime().isEqual(beforeAfterDateList.get(0));
            boolean isTaskDateOnMaxDateRange = task.getDateTime().isEqual(beforeAfterDateList.get(1));
            boolean isTaskEndDateAfterMinDateRange = task.getEndDateTime().isAfter(beforeAfterDateList.get(0));
            boolean isTaskEndDateBeforeMaxDateRange = task.getEndDateTime().isBefore(beforeAfterDateList.get(1));

            boolean isTaskDateValid = isTaskDateAfterMinDateRange && isTaskDateBeforeMaxDateRange;
            boolean isTaskEndDateValid = isTaskEndDateAfterMinDateRange && isTaskEndDateBeforeMaxDateRange;

            return (isTaskDateValid || isTaskEndDateValid || isTaskDateOnMinDateRange || isTaskDateOnMaxDateRange);
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskBetweenDatesPredicate // instanceof handles nulls
                && beforeAfterDateList.equals(((TaskBetweenDatesPredicate) other).beforeAfterDateList)); // state check
    }

}
