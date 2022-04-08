package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * GitUsername represents the Github userID of a Person.
 */
public class GitUsername {

    public static final String GIT_USERNAME_REGEX = "^[a-zA-Z0-9]+(-[a-zA-Z0-9]+){0,2}$";

    public static final String MESSAGE_CONSTRAINTS = "Github usernames should only contain alphanumeric"
            + " characters or single hyphens, and cannot begin or end with a hyphen.";

    private String userid;

    /**
     * Constructs a Github GitUsername for Person.
     *
     * @param userid String username
     */
    public GitUsername(String userid) {
        requireNonNull(userid);
        this.userid = userid;
    }

    /**
     * Returns username
     *
     * @return Github username of Person
     */
    public String getUsername() {
        return this.userid;
    }

    /**
     * Checks if Id is a valid Id. Id must be alphanumeric without a space in front.
     *
     * @param test Id being checked
     * @return True for valid id, false for invalid id.
     */
    public static boolean isValidId(String test) {
        return test.matches(GIT_USERNAME_REGEX);
    }

    @Override
    public String toString() {
        return this.userid;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof GitUsername)) {
            return false;
        }
        GitUsername otherGitUsername = (GitUsername) other;
        return this.userid.equals(otherGitUsername.userid);
    }

}
