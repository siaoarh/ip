import chatterbox.ChatterBotCore;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ChatterBotCore bot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/usagi.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the ChatterBotCore instance */
    public void setBot(ChatterBotCore bot) {
        this.bot = bot;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bot.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, botImage) // method name can stay for now
        );
        userInput.clear();
    }
}