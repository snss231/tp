package seedu.address.ui;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;


public class AlertWindow extends UiPart<Stage> {
    private static final String HEADER_MESSAGE = "ATTENTION! Below task(s) will due in one week from now:";
    private static final String FXML = "AlertWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(AlertWindow.class);



    @FXML
    private Label alertMessage;

    @FXML
    private Label alertHeaderMessage;

    /**
     * Creates a new AlertWindow.
     */
    public AlertWindow() {
        this(new Stage());
    }

    /**
     * Creates a new AlertWindow.
     *
     * @param root Stage to use as the root of the AlertWindow.
     */
    public AlertWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Shows the AlertWindow with the task reaching deadline soon.
     *
     * @param displayString String of tasks that is due soon.
     */
    public void display(String displayString) {
        alertHeaderMessage.setText(HEADER_MESSAGE);
        alertMessage.setText(displayString);
        logger.fine("Alert about the task deadline.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

}
