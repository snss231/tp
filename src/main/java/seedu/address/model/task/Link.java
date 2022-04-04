package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

public class Link {
    public static final String INVALID_LINK = "The link provided is not a valid URL!";
    private String link = "";

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
