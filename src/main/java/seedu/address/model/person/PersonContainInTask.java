package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person} matches any of the person object provided.
 */
public class PersonContainInTask implements Predicate<Person> {
    private final List<Person> targetPersonList;

    public PersonContainInTask(List<Person> targetPersonList) {
        this.targetPersonList = targetPersonList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Person person) {
        return targetPersonList.stream()
                .anyMatch(targetPerson -> targetPerson.isSamePerson(person));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this;
    }

}
