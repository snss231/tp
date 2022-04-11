package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command! Use 'help' for a list of commands";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The contact index provided is invalid!"
            + " Only a single non-zero positive integer is valid. Index must also not be out of bounds!\n"
            + "Examples of invalid indexes: -1, 1 abc, 1 i/(invalid parameter)";
    public static final String MESSAGE_INVALID_RECURRENCE = "The recurrence parameter is invalid! \n%1$s";
    public static final String MESSAGE_INVALID_INTERVAL = "The interval parameter is invalid! \n%1$s";
    public static final String MESSAGE_INVALID_RECURRENCE_INDEX = "The interval/recurrence indexes are invalid!"
            + " Only a single non-zero positive integer is valid. \n%1$s";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d contacts listed!";
    public static final String MESSAGE_TASKS_LISTED_OVERVIEW = "%1$d tasks listed!";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid!"
            + " Only non-zero positive values are valid. Index must also not be out of bounds!\n"
            + "Examples of invalid indexes: -1, 1 abc, 1 i/(invalid parameter)";
    public static final String MESSAGE_INVALID_DATE_RANGE = "The end date must be after the start date!";
    public static final String MESSAGE_NO_KEYWORDS = "You need to specify a keyword! \n%1$s";
    public static final String MESSAGE_INVALID_DATETIME = "The date and time provided is invalid!\n%1$s";
    public static final String MESSAGE_NEED_AT_LEAST_ONE_VALID_PARAMETER =
            "You need at least one valid parameter!\n%1$s";
    public static final String MESSAGE_INVALID_PARAMETERS = "Missing/Invalid parameters: ";

    public static final String MESSAGE_TAG_TOO_LONG = "Error: Tags can be at most 50 characters in length.\n"
            + "The following tag(s) are too long:\n%s";

    public static final String MESSAGE_DUPLICATE_GIT_USERNAME = "This Github username already exists in NUS Classes!\n"
            + "Check again?";
    public static final String MESSAGE_DUPLICATE_EMAIL = "This email already exists in NUS Classes!\nCheck again?";
    public static final String MESSAGE_DUPLICATE_PHONE = "This phone number already exists in NUS Classes!\n"
            + "Check again?";
}
