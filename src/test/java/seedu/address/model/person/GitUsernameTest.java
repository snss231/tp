package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;


public class GitUsernameTest {
    public String validUsername = "abc123"; //alnum
    public String validUsername2 = "a-bc"; //hyphens
    public String invalidUsername = "a___bc";
    public String invalidUsername2 = "a!bc";
    public String invalidUsername3 = "a|()";
    public String invalidUsername4 = "a--bc";
    public String invalidUsername5 = "-abc";
    public String invalidUsername6 = "abc-";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GitUsername(null));
    }

    @Test
    public void validUsername() {
        assertTrue(GitUsername.isValidId(validUsername));

        assertTrue(GitUsername.isValidId(validUsername2));
    }

    @Test
    public void invalidUsername() {
        assertFalse(GitUsername.isValidId(invalidUsername));

        assertFalse(GitUsername.isValidId(invalidUsername2));

        assertFalse(GitUsername.isValidId(invalidUsername3));

    }
}
