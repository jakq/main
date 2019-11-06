package seedu.address.logic.parser;

import seedu.address.logic.commands.ChangeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.nio.file.Paths;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;

/**
 * Parses input arguments and creates a new ChangeCommand object
 */
public class ChangeCommandParser implements Parser<ChangeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ChangeCommand
     * and returns a ChangeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FILE);
        if (!isPrefixPresent(argMultimap, PREFIX_FILE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeCommand.MESSAGE_USAGE));
        }
        args = ParserUtil.parseFile(argMultimap.getValue(PREFIX_FILE).get());

        return new ChangeCommand(Paths.get("data", args));
    }

    /**
     * Returns true if the prefix does not contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}