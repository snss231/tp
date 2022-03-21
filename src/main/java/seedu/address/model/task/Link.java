package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

public class Link {
    public static final String MESSAGE_CONSTRAINTS =
            "Link provided must be a valid link, and it should not be blank";

    public final String link;

    /**
     * Constructs a {@code Link}.
     *
     * @param link A link
     */
    public Link(String link) {
        requireNonNull(link);
        this.link = link;
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }

    @Override
    public String toString() {
        return link;
    }
}
