package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FILE_WITH_PREFIX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LoadCommand;

public class LoadCommandParserTest {
    private LoadCommandParser parser = new LoadCommandParser();

    @Test
    public void parse_fileFieldPresent_success() {
        Path expectedFile = Paths.get("data", "EatMe.json");

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_FILE_WITH_PREFIX, new LoadCommand(expectedFile));
    }
}
