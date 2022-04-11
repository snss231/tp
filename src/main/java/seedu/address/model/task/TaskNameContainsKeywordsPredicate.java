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
            Tag[] tagsArr = new Tag[task.getTags().size()];
            task.getTags().toArray(tagsArr);
            for (int i = 0; i < tagsArr.length; i++) {
                if (StringUtil.containsWordIgnoreCase(tagsArr[i].toString(), str.trim())) {
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
                && areKeywordsEqual(this.keywords, ((TaskNameContainsKeywordsPredicate) other)
                    .keywords)); // state check
    }

    private boolean areKeywordsEqual(List<String> list1, List<String> list2) {
        if (list1.size() == list2.size()) {
            boolean toReturn = false;
            for (int i = 0; i < list1.size(); i++) {
                toReturn = list1.get(i).equals(list2.get(i));
            }
            return toReturn;
        } else {
            return false;
        }

    }

}
