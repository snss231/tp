package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class TaskNameContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public TaskNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        boolean isEqual = false;
        for (String str: keywords) {
            if (StringUtil.containsWordIgnoreCase(task.getName(), str.trim())) {
                isEqual = true;
                break;
            }
            Tag[] tags = new Tag[task.getTags().size()];
            task.getTags().toArray(tags);
            for (int i = 0; i < tags.length; i++ ) {
                if (StringUtil.containsWordIgnoreCase(tags[i].toString(), str.trim())) {
                    isEqual = true;
                    break;
                }
            }
            if (isEqual) {
                break;
            }
        }
        return isEqual;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TaskNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
