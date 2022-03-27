package seedu.address.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Comparator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    // Credit: Image icon taken from https://icons8.com.
    private static final String TICK_ICON = "/images/tick.png";
    private static final String UNTICK_ICON = "/images/untick.png";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private FlowPane tags;
    @FXML
    private Hyperlink link;
    @FXML
    private ImageView markImage;
    @FXML
    private Label linkLabel;

    /**
     * Creates a {@code TaskCode} with the given {@code Task} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");

        name.setText(task.getName());
        setTaskColor(task.getDateTime());
        date.setText("Due: " + task.getDateTimeString());

        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        setLink();

        setMarkedImage(task);
    }

    public void setTaskColor(LocalDateTime taskDateTime) {
        LocalDateTime todayDate = LocalDateTime.now();
        if (taskDateTime.isBefore(todayDate)) {
            id.getStyleClass().add("cell_big_label_late");
            name.getStyleClass().add("cell_big_label_late");
            date.getStyleClass().add("cell_small_label_late");
        } else if (taskDateTime.isBefore(todayDate.plusDays(3))) {
            id.getStyleClass().add("cell_big_label_soon");
            name.getStyleClass().add("cell_big_label_soon");
            date.getStyleClass().add("cell_small_label_soon");
        } else {
            id.getStyleClass().add("cell_big_label");
            name.getStyleClass().add("cell_big_label");
            date.getStyleClass().add("cell_small_label");
        }
    }

    public void setMarkedImage(Task task) {
        if (task.isTaskMark()) {
            markImage.setImage(new Image(TICK_ICON));
        } else {
            markImage.setImage(new Image(UNTICK_ICON));
        }

    }

    public void setLink() {
        if (task.getLink().toString() != "") {
            linkLabel.setText("Link:");
            link.setText(task.getLink().toString());
            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Desktop.getDesktop().browse(new URI(link.getText()));
                    } catch (URISyntaxException | IOException e) {
                        link.getStyleClass().add("cell_small_hyperlink_invalid");
                    }
                }
            });

        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
