package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class GitUsernameTest {
    private String validUsername = "abc123"; //alnum
    private String validUsername2 = "a-bc"; //hyphens
    private String invalidUsername = "a___bc";
    private String invalidUsername2 = "a!bc";
    private String invalidUsername3 = "a|()";
    private String invalidUsername4 = "a--bc";
    private String invalidUsername5 = "-abc";
    private String invalidUsername6 = "abc-";

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
