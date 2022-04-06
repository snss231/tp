package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

public class Link {
    public static final String MESSAGE_CONSTRAINTS = "The link provided should follows the proper URL format.";
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
