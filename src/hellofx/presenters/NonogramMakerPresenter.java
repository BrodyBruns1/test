package hellofx.presenters;

import java.io.File;
import java.io.IOException;

import hellofx.handlers.OpenHandler;
import hellofx.handlers.SaveHandler;
import hellofx.handlers.ToggleButtonEventHandler;
import hellofx.interfaces.Openable;
import hellofx.interfaces.Saveable;
import hellofx.models.NonogramMakerModel;
import hellofx.views.NonogramMakerView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * The presenter class for the Nonogram Maker application.
 * Handles the logic and interaction between the model and view.
 */
public class NonogramMakerPresenter implements Openable, Saveable {

    private NonogramMakerView view;
    private NonogramMakerModel model;
    private int cellLength;

    /**
     * Constructs a NonogramMakerPresenter object with the specified number of rows,
     * columns, and cell length.
     * Initializes the model, view, and other components.
     * 
     * @param numRows    The number of rows in the nonogram grid.
     * @param numCols    The number of columns in the nonogram grid.
     * @param cellLength The length of each cell in pixels.
     */
    public NonogramMakerPresenter(int numRows, int numCols, int cellLength) {
        this.cellLength = cellLength;
        model = new NonogramMakerModel(numRows, numCols);
        view = new NonogramMakerView(numRows, numCols, cellLength);
        init();
    }

    /**
     * Retrieves the window associated with the view.
     * 
     * @return The window associated with the view, or null if not available.
     */
    private Window getWindow() {
        try {
            Window ret = view.getPane().getScene().getWindow();
            return ret;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Initializes the menu and buttons.
     */
    private void init() {
        initToggleButtons();
        bindToggleButtons();
        configureMenuItems();
    }

    /**
     * Initializes the toggle buttons in the view.
     * Sets the initial state of the buttons based on the model.
     * Adjusts the window size to fit the content.
     */
    private void initToggleButtons() {
        view.initButtons(model.getNumRows(), model.getNumCols(), cellLength);
        if (getWindow() != null) {
            getWindow().sizeToScene();
        }
    }

    /**
     * Binds the toggle buttons in the view to the corresponding cells in the model.
     * Sets the initial state of the buttons based on the model.
     * Attaches event handlers to the buttons.
     */
    private void bindToggleButtons() {
        for (int rowIdx = 0; rowIdx < model.getNumRows(); rowIdx++) {
            for (int colIdx = 0; colIdx < model.getNumCols(); colIdx++) {
                ToggleButton button = view.getToggleButton(rowIdx, colIdx);
                button.setSelected(model.getCell(rowIdx, colIdx));
                button.setOnAction(new ToggleButtonEventHandler(model, rowIdx, colIdx));
            }
        }
    }

    /**
     * Configures the menu items in the view.
     * Sets up the open and save file functionality.
     */
    private void configureMenuItems() {
        // Save file
        FileChooser saveChooser = new FileChooser();
        saveChooser.setTitle("Save");
        saveChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        saveChooser.setInitialDirectory(new File("."));
        view.getMenuItem(view.MENU_ITEM_SAVE).setOnAction(new SaveHandler(getWindow(), saveChooser, this));

        // Open file
        FileChooser openChooser = new FileChooser();
        openChooser.setTitle("Open");
        openChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        openChooser.setInitialDirectory(new File("."));
        view.getMenuItem(view.MENU_ITEM_OPEN).setOnAction(new OpenHandler(getWindow(), openChooser, this));
    }

    /**
     * Retrieves the pane associated with the view.
     * 
     * @return The pane associated with the view.
     */
    public Pane getPane() {
        return view.getPane();
    }

    /**
     * Opens a nonogram file and updates the model and view accordingly.
     * 
     * @param file The nonogram file to open.
     * @throws IOException If an I/O error occurs while opening the file.
     */
    public void open(File file) throws IOException {
        model = new NonogramMakerModel(file);
        init();
    }

    /**
     * Saves the current nonogram to a file with the specified name.
     * 
     * @param fileName The name of the file to save the nonogram to.
     * @throws IOException If an I/O error occurs while saving the file.
     */
    public void save(String fileName) throws IOException {
        model.saveToFile(fileName);
    }
}
