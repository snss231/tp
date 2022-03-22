package seedu.address.model.task;

import seedu.address.commons.util.StringUtil;

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
        return (task.getDateTime().isBefore(beforeAfterDates.get(0))
            && task.getDateTime().isAfter(beforeAfterDates.get(1)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskBetweenDatesPredicate // instanceof handles nulls
                && beforeAfterDates.equals(((TaskBetweenDatesPredicate) other).beforeAfterDates)); // state check
    }

}
