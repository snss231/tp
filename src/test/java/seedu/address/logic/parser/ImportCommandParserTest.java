package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ImportCommandParser.ERROR_INVALID_EMAIL;
import static seedu.address.logic.parser.ImportCommandParser.ERROR_INVALID_GITHUB;
import static seedu.address.logic.parser.ImportCommandParser.ERROR_INVALID_NAME;
import static seedu.address.logic.parser.ImportCommandParser.ERROR_INVALID_PHONE;
import static seedu.address.logic.parser.ImportCommandParser.ERROR_INVALID_TAG;
import static seedu.address.logic.parser.ImportCommandParser.MESSAGE_CSV_MISSING_HEADERS;
import static seedu.address.logic.parser.ImportCommandParser.MESSAGE_FILE_DOES_NOT_EXIST;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class ImportCommandParserTest {

    @Test
    void parse_validEmptyFile_success() {
        ImportCommandParser parser = new ImportCommandParser();

        Path path = Path.of("src", "test", "data", "ImportTestData", "empty.csv");

        String filepath = path.toString();

        assertParseSuccess(parser, ImportCommand.COMMAND_WORD + " " + PREFIX_FILEPATH + filepath,
                new ImportCommand(List.of(), filepath, List.of()));
    }

    @Test
    void parse_validFileWithEntries_success() {
        ImportCommandParser parser = new ImportCommandParser();

        Person a = new PersonBuilder().withName("Alex Tan").withEmail("e0315898@u.nus.edu").withUsername("alexcoder")
                .withPhone("97013404").withTags("Student", "Lab 14C").build();

        Person b = new PersonBuilder().withName("Brad Tay").withEmail("e03158448@u.nus.edu").withUsername("braddytay")
                .withPhone("97553402").withTags("TA", "Lab 12F").build();

        Path path = Path.of("src", "test", "data", "ImportTestData", "importTest.csv");

        String filepath = path.toString();

        assertParseSuccess(parser, ImportCommand.COMMAND_WORD + " " + PREFIX_FILEPATH + filepath,
                new ImportCommand(List.of(a, b), filepath, List.of()));
    }

    @Test
    void parse_missingHeaders_failure() {
        ImportCommandParser parser = new ImportCommandParser();

        Path path = Path.of("src", "test", "data", "ImportTestData", "missingHeaders.csv");

        String filepath = path.toString();

        assertParseFailure(parser, ImportCommand.COMMAND_WORD + " " + PREFIX_FILEPATH + filepath,
                String.format(MESSAGE_CSV_MISSING_HEADERS, filepath));
    }

    @Test
    void parse_missingFile_failure() {
        ImportCommandParser parser = new ImportCommandParser();

        String filepath = "foo/bar.csv";

        assertParseFailure(parser, ImportCommand.COMMAND_WORD + " " + PREFIX_FILEPATH + filepath,
                String.format(MESSAGE_FILE_DOES_NOT_EXIST, filepath));
    }

    @Test
    void parse_invalidFields_success() {
        ImportCommandParser parser = new ImportCommandParser();

        String filepath = "src/test/data/ImportTestData/invalidFields.csv";

        List<String> invalidFields = List.of(
            String.format(ERROR_INVALID_NAME, "2"),
            String.format(ERROR_INVALID_PHONE, "a"),
            String.format(ERROR_INVALID_EMAIL, "k"),
            String.format(ERROR_INVALID_GITHUB, "_"),
            String.format(ERROR_INVALID_TAG, "日本人")
        );

        assertParseSuccess(parser, ImportCommand.COMMAND_WORD + " " + PREFIX_FILEPATH + filepath,
                new ImportCommand(List.of(), filepath, invalidFields));
    }

    @Test
    void parse_extraneousHeaders_success() {
        ImportCommandParser parser = new ImportCommandParser();

        String filepath = "src/test/data/ImportTestData/extraneousHeaders.csv";

        assertParseSuccess(parser, ImportCommand.COMMAND_WORD + " " + PREFIX_FILEPATH + filepath,
                new ImportCommand(List.of(), filepath, List.of()));
    }
}
