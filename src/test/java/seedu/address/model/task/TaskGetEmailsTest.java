package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalAssignedTasks;

public class TaskGetEmailsTest {
    @Test
    void getEmails_multipleContacts_success() {
        Task task = TypicalAssignedTasks.getTypicalAssignedTaskList().getTaskList().get(0);
        List<Person> people = task.getPeople();
        assert !people.isEmpty();

        String expected = people.stream().map(p -> p.getEmail().toString()).collect(Collectors.joining(", "));
        String actual = task.getEmails();

        assertEquals(expected, actual);
    }
}
