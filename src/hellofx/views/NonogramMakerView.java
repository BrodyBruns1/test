package hellofx.views;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * The NonogramMakerView class represents the view component of the Nonogram
 * Maker application.
 * It provides the user interface for creating and editing nonogram puzzles.
 */
public class NonogramMakerView {
    private BorderPane borderPane;
    private MenuBar menuBar;
    private CellGridView cellGridView;
    private HashMap<String, MenuItem> MenuItemsMap;

    public String MENU_ITEM_OPEN = "MENU_ITEM_OPEN";
    public String MENU_ITEM_SAVE = "MENU_ITEM_SAVE";
    public String MENU_ITEM_EXIT = "MENU_ITEM_EXIT";

    /**
     * Constructs a NonogramMakerView object with the specified number of rows,
     * columns, and cell length.
     * 
     * @param numRows    the number of rows in the nonogram grid
     * @param numCols    the number of columns in the nonogram grid
     * @param cellLength the length of each cell in pixels
     */
    public NonogramMakerView(int numRows, int numCols, int cellLength) {
        cellGridView = new CellGridView(numRows, numCols, cellLength);
        borderPane = new BorderPane();
        initMenuBar();
        borderPane.setTop(menuBar);
        borderPane.setCenter(cellGridView.getPane());
    }

    /**
     * Initializes the menu bar with File menu and its associated menu items.
     * The menu items include Open, Save, and Exit.
     * The Exit menu item is set to close the application when clicked.
     */
    private void initMenuBar() {

        Menu menuFile = new Menu("File");
        MenuItem menuItemOpen = new MenuItem("_Open");
        MenuItem menuItemSave = new MenuItem("_Save");
        MenuItem menuItemExit = new MenuItem("_Exit");

        menuFile.getItems().addAll(menuItemOpen, menuItemSave, menuItemExit);

        MenuItemsMap = new HashMap<String, MenuItem>();
        MenuItemsMap.put(MENU_ITEM_OPEN, menuItemOpen);
        MenuItemsMap.put(MENU_ITEM_SAVE, menuItemSave);
        MenuItemsMap.put(MENU_ITEM_EXIT, menuItemExit);

        menuItemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        menuBar = new MenuBar();
        menuBar.getMenus().add(menuFile);
    };

    /**
     * Returns the MenuItem object associated with the specified name.
     * 
     * @param name the name of the MenuItem
     * @return the MenuItem object associated with the specified name, or null if
     *         not found
     */
    public MenuItem getMenuItem(String name) {
        return MenuItemsMap.get(name);
    }

    /**
     * Returns the Pane object representing the view.
     * 
     * @return the Pane object representing the view
     */
    public Pane getPane() {
        return borderPane;
    }

    /**
     * Initializes the buttons in the cell grid with the specified number of rows,
     * columns, and cell length.
     * 
     * @param numRows    the number of rows in the nonogram grid
     * @param numCols    the number of columns in the nonogram grid
     * @param cellLength the length of each cell in pixels
     */
    public void initButtons(int numRows, int numCols, int cellLength) {
        cellGridView.initButtons(numRows, numCols, cellLength);
    }

    /**
     * Returns the number of rows in the nonogram grid.
     * 
     * @return the number of rows in the nonogram grid
     */
    public int getNumRows() {
        return cellGridView.getNumRows();
    }

    /**
     * Returns the number of columns in the nonogram grid.
     * 
     * @return the number of columns in the nonogram grid
     */
    public int getNumCols() {
        return cellGridView.getNumCols();
    }

    /**
     * Returns the ToggleButton object at the specified row and column indices.
     * 
     * @param rowIdx the row index of the ToggleButton
     * @param colIdx the column index of the ToggleButton
     * @return the ToggleButton object at the specified row and column indices
     */
    public ToggleButton getToggleButton(int rowIdx, int colIdx) {
        return cellGridView.getToggleButton(rowIdx, colIdx);
    }
}
