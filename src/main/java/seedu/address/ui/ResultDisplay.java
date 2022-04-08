package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    private static String emails;

    @FXML
    private TextArea resultDisplay;

    @FXML
    private Button copyButton;

    /**
     * Creates the region where feedback will be displayed.
     */
    public ResultDisplay() {
        super(FXML);
        this.copyButton.setVisible(false);
    }

    @FXML
    private void copyEmails() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(emails);
        clipboard.setContent(content);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public void showCopy() {
        this.copyButton.setVisible(true);
    }

    public void hideCopy() {
        this.copyButton.setVisible(false);
    }
}
