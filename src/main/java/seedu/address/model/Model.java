package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUsername;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /** {@code Predicate} that evaluate to false if task is unmark */
    Predicate<Task> PREDICATE_SHOW_ALL_UNMARK_TASKS = task -> !task.isTaskMark();

    /** {@code Predicate} that evaluate to true if task is mark */
    Predicate<Task> PREDICATE_SHOW_ALL_MARK_TASKS = task -> task.isTaskMark();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same name as {@code Person} exists in NUS Classes
     */
    boolean hasName(Person person);

    /**
     * Returns true if a person with the same gitUsername as {@code gitUsername} exists in NUS Classes
     *
     * @param gitUsername
     * @return Whether username exists
     */
    boolean hasUsername(GitUsername gitUsername);

    /**
     * Checks if Model has Email already existing.
     *
     * @param email Email to be checked.
     * @return Whether email exists
     */
    boolean hasEmail(Email email);

    /**
     * Checks if Model has Phone already existing.
     *
     * @param phone Phone to be checked.
     * @return Whether phone exists.
     */
    boolean hasPhone(Phone phone);

    /**
     * Deletes person.
     *
     * @param target Person to be deleted.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    ReadOnlyTaskList getTaskList();

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Adds the task to the taskList.
     *
     * @param task the task to be added
     */
    void addTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the task list.
     */
    void deleteTask(Task target);

    void setTask(Task taskToEdit, Task editedTask);

    /**
     * Returns true if a task with the same description as {@code task} exists in the task list.
     */
    boolean hasTask(Task task);

    /**
     * Marks the given task as completed.
     *
     * @param task the task to be marked.
     */
    void markTask(Task task);

    /**
     * Unmarks the given task as not complete.
     *
     * @param task the task to be unmarked.
     */
    void unmarkTask(Task task);

    void setTaskList(ReadOnlyTaskList tasks);
}
