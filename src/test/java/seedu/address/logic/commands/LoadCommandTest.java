package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEateries.getTypicalOpenAddressBook;
import static seedu.address.testutil.TypicalFeeds.getTypicalFeedList;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class LoadCommandTest {
    private Model model;
    private Model updatedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalOpenAddressBook(), getTypicalFeedList(), new UserPrefs());
        UserPrefs userPrefsTest = new UserPrefs();
        userPrefsTest.setAddressBookFilePath(Paths.get("src", "test", "data", "LoadCommandTest", "EatMe.json"));
        model.setUserPrefs(userPrefsTest);
    }

    @Test
    public void execute_duplicateFileLoad_throwsParseException() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("data", System.getProperty("user.name") + ".json"));
        model.setUserPrefs(userPrefs);

        assertCommandFailure(new LoadCommand(), model,
                String.format(LoadCommand.MESSAGE_ALREADY_OPENED, model.getAddressBookFilePath()));
    }

    @Test
    public void execute_fileLoadNotFound_throwsParseException() {
        Path nonExistentFile = Paths.get("src", "test", "data", "LoadCommandTest", "computing.json");

        assertCommandFailure(new LoadCommand(nonExistentFile), model,
                String.format(LoadCommand.MESSAGE_NOT_FOUND, nonExistentFile));
    }

    @Test
    public void execute_fileLoad_success() {
        updatedModel = model;
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("src", "test", "data", "LoadCommandTest", "utown.json"));
        updatedModel.setUserPrefs(userPrefs);

        Path loadedFile = Paths.get("src", "test", "data", "LoadCommandTest", "utown.json");

        assertCommandSuccess(new LoadCommand(loadedFile), model,
                String.format(LoadCommand.MESSAGE_SUCCESS_CHANGE, loadedFile), updatedModel);
    }

    @Test
    public void execute_fileLoadOriginal_success() {
        updatedModel = model;
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("src", "test", "data", "LoadCommandTest", "utown.json"));
        updatedModel.setUserPrefs(userPrefs);
        System.out.println("Actual: " + model.getAddressBookFilePath());
        System.out.println("Expected: " + updatedModel.getAddressBookFilePath());
        assertCommandSuccess(new LoadCommand(), updatedModel, String.format(LoadCommand.MESSAGE_SUCCESS_CHANGE,
                model.getAddressBookFilePath()), model);
    }

}
