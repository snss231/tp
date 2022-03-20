package seedu.address.model.person;

/**
 * Username represents the Github userID of a Person.
 */
public class Username {
    public String userid;
    public static final String MESSAGE_CONSTRAINTS = "Username should adhere to the following constraints:\n"
            + "1. AlphaNumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}]*";


    /**
     * Constructs a Github Username for Person.
     *
     * @param userid String username
     */
    public Username(String userid) {
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
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.userid;
    }

}
