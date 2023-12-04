package hellofx.handlers;

import javafx.event.EventHandler;

import java.io.File;
import java.io.IOException;

import hellofx.interfaces.Saveable;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * The SaveHandler class is responsible for handling the save action in a JavaFX
 * application.
 */
public class SaveHandler extends AbstractBaseHandler implements EventHandler<ActionEvent> {
    private Saveable saver;

    /**
     * Constructs a SaveHandler object with the specified window, fileChooser, and
     * saver.
     *
     * @param window      the window in which the file chooser dialog will be
     *                    displayed
     * @param fileChooser the file chooser used to select the file to save to
     * @param saver       the Saveable object used to save data to a file
     */
    public SaveHandler(Window window, FileChooser fileChooser, Saveable saver) {
        super(window, fileChooser);
        this.saver = saver;
    }

    /**
     * Handles the save action triggered by an ActionEvent.
     * Displays a file chooser dialog to select the file to save to.
     *
     * @param event the ActionEvent triggered by the save action
     */
    @Override
    public void handle(ActionEvent event) {
        File s = fileChooser.showSaveDialog(window);
        try {
            if (s != null) {
                saver.save(s.getName());
            }
        } catch (IOException e) {
            // Handle the exception if saving fails
        }
    }
}
