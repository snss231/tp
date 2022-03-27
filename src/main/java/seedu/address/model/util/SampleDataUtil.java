package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Link;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Joseph"), new Phone("89993233"), new Email("Joseph@nus.edu.sg"),
                new GitUsername(""), getTagSet("Colleague", "Lecturer")),
            new Person(new Name("Example TA"), new Phone("92624417"), new Email("e111111@u.nus.edu"),
                    new GitUsername("TACS2103T"),
                    getTagSet("TA", "T-12")),
            new Person(new Name("Brian Chow"), new Phone("87438807"), new Email("e123456@u.nus.edu"),
                new GitUsername("brian16600"),
                getTagSet("CS2101 Group 4", "CS2103T Lab 12")),
            new Person(new Name("Sean Ng"), new Phone("99272758"), new Email("e234567@u.nus.edu"),
                new GitUsername("snss231"),
                getTagSet("Teammate", "CS2101 Group 4")),
            new Person(new Name("Adrian Ong"), new Phone("93210283"), new Email("e345678@u.nus.edu"),
                new GitUsername("adrianongjj"),
                getTagSet("CS2103T Lab 12")),
            new Person(new Name("Ong Jun Jie"), new Phone("91031282"), new Email("e456789@u.nus.edu"),
                new GitUsername("junjunjieong"),
                getTagSet("CS2107", "CS2040")),
            new Person(new Name("Jun Rong"), new Phone("92492021"), new Email("e567890@u.nus.edu"),
                new GitUsername("junrong98"),
                getTagSet("CS2030", "CS2107"))
        };
    }


    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task("CS2103T Lecture",
                    LocalDateTime.of(2022, 4, 8, 14, 0),
                    getTagSet("Week 12 Lecture"),
                    new Link("https://nus-sg.zoom.us/j/92307270969?pwd=VVMvNWFPWFpyVHRIcXR0VkJlNkg0dz09"),
                    false),
            new Task("Meeting with TAs", LocalDateTime.now().minusDays(2),
                    getTagSet("Discuss tutorials"), new Link(""), false),
            new Task("CS2103T Lecture",
                    LocalDateTime.of(2022, 4, 15, 14, 0),
                    getTagSet("Week 13 Lecture"),
                    new Link("https://nus-sg.zoom.us/j/92307270969?pwd=VVMvNWFPWFpyVHRIcXR0VkJlNkg0dz09")),
            new Task("Consultation with students", LocalDateTime.now().plusDays(1),
                    getTagSet("Consultation"),
                    new Link("https://nus-sg.zoom.us/j/92307270969?pwd=VVMvNWFPWFpyVHRIcXR0VkJlNkg0dz09"),
                    false),
            new Task("Meeting with exam invigilators", LocalDateTime.now().plusWeeks(2) ,
                    getTagSet("Meeting"), new Link(""), false),
            new Task("CS2103T Lecture",
                    LocalDateTime.of(2022, 4, 22, 14, 0),
                    getTagSet("Week 14 Lecture"),
                    new Link("https://nus-sg.zoom.us/j/92307270969?pwd=VVMvNWFPWFpyVHRIcXR0VkJlNkg0dz09"),
                    false)
        };
    }

    public static ReadOnlyTaskList getSampleTaskList() {
        TaskList sampleTl = new TaskList();
        for (Task sampleTask : getSampleTasks()) {
            sampleTl.addTask(sampleTask);
        }
        return sampleTl;
    }

}
