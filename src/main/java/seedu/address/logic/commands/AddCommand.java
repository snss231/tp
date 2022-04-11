package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_EMAIL;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_GIT_USERNAME;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.TagUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "addc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to NUS Classes.\n"
            + "Usage: "
            + COMMAND_WORD + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GIT_USERNAME + "GITHUB_USERNAME "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_GIT_USERNAME + "john123 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";


    private final Person toAdd;

    /**
     * Constructs an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPhone(toAdd.getPhone())) {
            throw new CommandException(MESSAGE_DUPLICATE_PHONE);
        }

        if (model.hasEmail(toAdd.getEmail())) {
            throw new CommandException(MESSAGE_DUPLICATE_EMAIL);
        }

        if (model.hasUsername(toAdd.getUsername())) {
            throw new CommandException(MESSAGE_DUPLICATE_GIT_USERNAME);
        }

        String checkTagLength = TagUtil.checkTagLength(toAdd.getTags());

        //null value represents no tags are too long.
        if (checkTagLength != null) {
            throw new CommandException(checkTagLength);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
