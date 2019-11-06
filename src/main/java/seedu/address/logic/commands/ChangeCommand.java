package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE;

/**
 * Lists all persons in the address book to the user.
 */
public class ChangeCommand extends Command {

    public static final String COMMAND_WORD = "change";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the EatMe.json file being read. "
            + "Parameters: "
            + PREFIX_FILE + " FILENAME.json";

    public static final String MESSAGE_SUCCESS_CHANGE = "File Changed: %1$s. Please relaunch EatMe!";
    public static final String MESSAGE_ALREADY_OPENED = "%1$s is already opened.";
    public static final String MESSAGE_NOT_FOUND = "Please check that %1$s exists.";
    public static final String FILE_CONSTRAINTS = "\\f must be followed by a file name.";

    private final Path toChange;

    /**
     * Creates a ChangeCommand to add the specified {@code Path}
     */
    public ChangeCommand(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        toChange = addressBookFilePath;
    }

    public ChangeCommand() {
        toChange = Paths.get("data", System.getProperty("user.name") + ".json");
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getAddressBookFilePath().equals(toChange)) {
            throw new CommandException(String.format(MESSAGE_ALREADY_OPENED, toChange));
        } else if (!checkFile(toChange)) {
            throw new CommandException(String.format(MESSAGE_NOT_FOUND, toChange));
        }
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(toChange);
        model.setUserPrefs(userPrefs);
        return new CommandResult(String.format(MESSAGE_SUCCESS_CHANGE, toChange));
    }

    protected boolean checkFile(Path toChange) {
        File folder = new File("data");
        File[] listOfFiles = folder.listFiles();
        boolean exists = false;

        for (int i = 0; i < listOfFiles.length; i++) {
            exists = listOfFiles[i].toPath().equals(toChange);
            if (exists) {
                break;
            }
        }
        return exists;
    }
}
