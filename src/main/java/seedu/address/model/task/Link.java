package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

public class Link {
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
