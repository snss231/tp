package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;


import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingIndex_failure() {
        // no index specified
        assertParseFailure(parser, " ", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

    }

    @Test
    public void parse_invalidIndex_failure() {
        //negative index without parameters
        assertParseFailure(parser, "-1 ", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        //negative index with parameters
        assertParseFailure(parser, "-1 " + PREFIX_NAME + VALID_USERNAME_BOB,
                MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test public void parse_noParameters_failure() {
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void parse_validInput_success() {
        // With name
        EditPersonDescriptor descriptorWithName = new EditPersonDescriptor();
        descriptorWithName.setName(new Name(VALID_NAME_BOB));
        assertParseSuccess(parser, "1 " + PREFIX_NAME + VALID_NAME_BOB,
                new EditCommand(Index.fromZeroBased(0), descriptorWithName));

        //With phone
        EditPersonDescriptor descriptorWithPhone = new EditPersonDescriptor();
        descriptorWithPhone.setPhone(new Phone(VALID_PHONE_AMY));
        assertParseSuccess(parser, "1 " + PREFIX_PHONE + VALID_PHONE_AMY,
                new EditCommand(Index.fromZeroBased(0), descriptorWithPhone));

        //With email
        EditPersonDescriptor descriptorWithEmail = new EditPersonDescriptor();
        descriptorWithEmail.setEmail(new Email(VALID_EMAIL_BOB));
        assertParseSuccess(parser, "1 " + PREFIX_EMAIL + VALID_EMAIL_BOB,
                new EditCommand(Index.fromZeroBased(0), descriptorWithEmail));

        //With username
        EditPersonDescriptor descriptorWithUsername = new EditPersonDescriptor();
        descriptorWithUsername.setUsername(new GitUsername(VALID_USERNAME_AMY));
        assertParseSuccess(parser, "1 " + PREFIX_GIT_USERNAME + VALID_USERNAME_AMY,
                new EditCommand(Index.fromZeroBased(0), descriptorWithUsername));

        //With tag
        EditPersonDescriptor descriptorWithTag = new EditPersonDescriptor();
        Set<Tag> set = new HashSet<>();
        set.add(new Tag(VALID_TAG_FRIEND));
        set.add(new Tag(VALID_TAG_HUSBAND));
        descriptorWithTag.setTags(set);
        assertParseSuccess(parser, "3 " + PREFIX_TAG + VALID_TAG_FRIEND + " " + PREFIX_TAG + VALID_TAG_HUSBAND,
                new EditCommand(Index.fromZeroBased(2), descriptorWithTag));

        //With all valid
        EditPersonDescriptor descriptorWithAll = new EditPersonDescriptor();
        descriptorWithAll.setName(new Name(VALID_NAME_BOB));
        descriptorWithAll.setPhone(new Phone(VALID_PHONE_AMY));
        descriptorWithAll.setEmail(new Email(VALID_EMAIL_BOB));
        descriptorWithAll.setUsername(new GitUsername(VALID_USERNAME_AMY));
        Set<Tag> allSet = new HashSet<>();
        allSet.add(new Tag(VALID_TAG_FRIEND));
        allSet.add(new Tag(VALID_TAG_HUSBAND));
        descriptorWithAll.setTags(allSet);
        assertParseSuccess(parser, "2 " + PREFIX_NAME + VALID_NAME_BOB + " " + PREFIX_PHONE + VALID_PHONE_AMY
                + " " + PREFIX_EMAIL + VALID_EMAIL_BOB + " " + PREFIX_GIT_USERNAME + VALID_USERNAME_AMY + " "
                + PREFIX_TAG + VALID_TAG_FRIEND + " " + PREFIX_TAG + VALID_TAG_HUSBAND,
                new EditCommand(Index.fromZeroBased(1), descriptorWithAll));
    }

}
