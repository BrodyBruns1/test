package hellofx.handlers;

import java.io.File;
import java.io.IOException;

import hellofx.interfaces.Openable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * This class represents an event handler for opening files. It extends the
 * AbstractBaseHandler class
 * and implements the EventHandler interface. It provides functionality for
 * opening a file using a FileChooser
 * and an Openable object.
 */
public class OpenHandler extends AbstractBaseHandler implements EventHandler<ActionEvent> {
    private Openable opener;

    /**
     * Constructs a new OpenHandler with the specified Window, FileChooser, and
     * Openable objects.
     * 
     * @param window      the Window object used for displaying dialogs
     * @param fileChooser the FileChooser object used for opening files
     * @param opener      the Openable object used for opening files
     */
    public OpenHandler(Window window, FileChooser fileChooser, Openable opener) {
        super(window, fileChooser);
        this.opener = opener;
    }

    /**
     * Handles the ActionEvent triggered by the user. Shows the FileChooser dialog
     * to select a file.
     * 
     * @param event the ActionEvent object representing the user's action
     */
    @Override
    public void handle(ActionEvent event) {
        File o = fileChooser.showOpenDialog(window);
        try {
            if (o != null) {
                opener.open(o);
            }
        } catch (IOException e) {
            // Handle any IOException that occurs during file opening
        }
    }
}