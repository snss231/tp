package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("$#")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("12345")); // numbers only
        assertFalse(Name.isValidName("peter*")); // contains invalid symbols
        assertFalse(Name.isValidName("peter the 2nd")); // alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr Second")); // long names
        assertTrue(Name.isValidName("John O'Reilly")); // name with apostrophes
        assertTrue(Name.isValidName("Martin Luther King, Jr.")); // name with full stop and comma
        assertTrue(Name.isValidName("Alex Smith-Jones")); // name with hyphen
        assertTrue(Name.isValidName("Rajesh s/o Koothrapali")); // name with slash
    }
}
