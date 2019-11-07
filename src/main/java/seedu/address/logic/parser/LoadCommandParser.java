package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.logic.commands.LoadCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ChangeCommand object
 */
public class LoadCommandParser implements Parser<LoadCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ChangeCommand
     * and returns a ChangeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LoadCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FILE);
        if (!isPrefixPresent(argMultimap, PREFIX_FILE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LoadCommand.MESSAGE_USAGE));
        }
        args = ParserUtil.parseFile(argMultimap.getValue(PREFIX_FILE).get());
        Path fileToLoad = Paths.get("data", args);
        return new LoadCommand(fileToLoad);
    }

    /**
     * Returns true if the prefix does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}
