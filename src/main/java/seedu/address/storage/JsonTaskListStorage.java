package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;


/**
 * JSON Storage for Tasks.
 */
public class JsonTaskListStorage implements TaskListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskListStorage.class);

    /* FilePath */
    private Path filePath;

    /**
     * Constructor for JsonTasksStorage.
     *
     * @param filePath FilePath destination.
     */
    public JsonTaskListStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns FilePath.
     *
     * @return FilePath of Tasks.
     */
    public Path getTaskListFilePath() {
        return filePath;
    }

    /**
     * Reads Tasks
     *
     * @return Optional Object containing Tasks.
     * @throws DataConversionException if data is in incorrect format in filepath.
     */
    public Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException {
        return readTaskList(filePath);
    }

    /**
     * Reads Tasks from JSON.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<ReadOnlyTaskList> readTaskList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskList> jsonTaskList = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskList.class);

        if (!jsonTaskList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTaskList(ReadOnlyTaskList taskList) throws IOException {
        saveTaskList(taskList, filePath);
    }

    @Override
    public void saveTaskList(ReadOnlyTaskList taskList, Path filePath) throws IOException {
        requireNonNull(taskList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskList(taskList), filePath);
    }

    /**
     * Saves Tasks into JSON.
     *
     * @param taskList Tasks object containing tasklist to be saved
     * @throws IOException
     */
    public void saveTasks(TaskList taskList) throws IOException {
        JsonUtil.saveJsonFile(taskList, filePath);
    }
}
