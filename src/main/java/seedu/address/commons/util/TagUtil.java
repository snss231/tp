package seedu.address.commons.util;

import static seedu.address.commons.core.Messages.MESSAGE_TAG_TOO_LONG;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Static library of methods related to Tags
 */
public class TagUtil {

    /**
     * Checks if any of the tags is too long (more than 50 characters).
     *
     * @return null if none of the tags are too long, error message otherwise.
     */
    public static String checkTagLength(Set<Tag> tags) {
        if (tags.stream().anyMatch(tag -> tag.tagName.length() > 50)) {
            return String.format(MESSAGE_TAG_TOO_LONG,
                    tags.stream()
                            .filter(tag -> tag.tagName.length() > 50)
                            .reduce("", (str, tag) -> tag + "\n" + str, (s1, s2) -> s1 + s2));
        }
        return null;
    }
}
