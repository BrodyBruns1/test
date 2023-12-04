package hellofx.handlers;

import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * The AbstractBaseHandler class is a base class for other handlers in the
 * application.
 */
public class AbstractBaseHandler {
    protected Window window;
    protected FileChooser fileChooser;

    /**
     * Constructs a new AbstractBaseHandler with the specified window and file
     * chooser.
     *
     * @param window      the window to use for displaying dialogs
     * @param fileChooser the file chooser to use for opening and saving files
     */
    protected AbstractBaseHandler(Window window, FileChooser fileChooser) {
        this.window = window;
        this.fileChooser = fileChooser;
    }
}
