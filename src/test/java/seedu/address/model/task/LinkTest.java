package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LinkTest {
    @Test
    public void constructor_null_throwsIllegalArgumentException() {
        assertThrows(NullPointerException.class, () -> new Link(null));
    }

    @Test
    public void isValidLink() {
        // null link
        assertTrue(() -> new Link().isEmpty());

        // blank link
        assertFalse(Link.isValidLink(""));
        assertFalse(Link.isValidLink(" "));

        // missing parts
        assertFalse(Link.isValidLink("a.com"));
        assertFalse(Link.isValidLink("google"));

        // invalid links
        assertFalse(Link.isValidLink("www.google.com"));
        assertFalse(Link.isValidLink("www.google.com.sg"));

        // valid link
        assertTrue(Link.isValidLink("http://info.cern.ch/"));
        assertTrue(Link.isValidLink("https:google.com"));
        assertTrue(Link.isValidLink("https:google.com.sg"));
        assertTrue(Link.isValidLink("https://www.coingecko.com"));
        assertTrue(Link.isValidLink("https://www.thisisthelongesteuropeandomainnameallovertheworldandnowitismine.eu"));
        assertTrue(
                Link.isValidLink("https://nus-sg.zoom.us/j/92307270969?pwd=VVMvNWFPWFpyHRIcXR0VkJlNkg0dz09#success"));
    }
}
