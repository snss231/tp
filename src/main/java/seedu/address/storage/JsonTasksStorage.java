package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Tasks;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * JSON Storage for Tasks.
 */
public class JsonTasksStorage {

    /* FilePath */
    private Path filePath;

    /**
     * Constructor for JsonTasksStorage.
     *
     * @param filePath FilePath destination.
     */
    public JsonTasksStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns FilePath.
     *
     * @return FilePath of Tasks.
     */
    public Path getJsonTasksFilePath() {
        return filePath;
    }

    /**
     * Reads Tasks
     *
     * @return Optional Object containing Tasks.
     * @throws DataConversionException if data is in incorrect format in filepath.
     */
    public Optional<Tasks> readTasks() throws DataConversionException {
        return readTasks(filePath);
    }

    /**
     * Reads Tasks from JSON.
     *
     * @param tasksFilePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<Tasks> readTasks(Path tasksFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(tasksFilePath, Tasks.class);
    }

    /**
     * Saves Tasks into JSON.
     *
     * @param tasks Tasks object containing tasklist to be saved
     * @throws IOException
     */
    public void saveTasks(Tasks tasks) throws IOException {
        JsonUtil.saveJsonFile(tasks, filePath);
    }
}
