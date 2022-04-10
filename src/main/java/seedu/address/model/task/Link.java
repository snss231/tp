package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Link {
    public static final String MESSAGE_CONSTRAINTS = "The link provided should follows the proper URL format "
            + "(must have reference to 'https://' or 'http://'. E.g. www.google.com will not be considered a valid URL"
            + " whereas https://www.google.com will be considered a valid URL.";
    private String link;

    /**
     * Constructs a {@code Link}.
     *
     * @param link A link
     */
    public Link(String link) {
        requireNonNull(link);

        this.link = link;
    }

    public Link() {
        this.link = null;
    }

    /**
     * Checks if link is valid.
     * @param link Link of users.
     * @throws IllegalArgumentException If link is not a valid URL.
     */
    public static boolean isValidLink(String link) {
        try {
            new URL(link).toURI();
        } catch (URISyntaxException | MalformedURLException e) {
            // Unconventional, but currently only way to check whether a link is valid.
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || this.link.equals(((Link) other).link);
    }

    @Override
    public String toString() {
        return link;
    }

    public boolean isEmpty() {
        return link == null;
    }
}
