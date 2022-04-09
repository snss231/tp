package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskNameContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TaskNameContainsKeywordsPredicate firstPredicate =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskNameContainsKeywordsPredicate secondPredicate =
                new TaskNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskNameContainsKeywordsPredicate firstPredicateCopy =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskNameContainsKeywords_returnsTrue() {
        // One keyword
        TaskNameContainsKeywordsPredicate predicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("Consultation"));
        assertTrue(predicate.test(new TaskBuilder().withTaskName("Consultation Alice").build()));

        // Multiple keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Consultation", "Slot"));
        assertTrue(predicate.test(new TaskBuilder().withTaskName("Consultation Slot").build()));

        // Only one matching keyword
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Consultation", "Meeting"));
        assertTrue(predicate.test(new TaskBuilder().withTaskName("Consultation World Org").build()));

        // Mixed-case keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("coNsULtAtion", "meEtIng"));
        assertTrue(predicate.test(new TaskBuilder().withTaskName("Consultation Meeting").build()));
    }

    @Test
    public void test_taskNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withTaskName("Consultation").build()));

        // Non-matching keyword
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Apple"));
        assertFalse(predicate.test(new TaskBuilder().withTaskName("Consultation Meeting").build()));

    }
}
