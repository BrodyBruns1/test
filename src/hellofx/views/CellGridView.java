package hellofx.views;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * The CellGridView class represents a grid view of toggle buttons.
 * It provides methods to initialize the buttons, retrieve the number of rows
 * and columns,
 * retrieve a specific toggle button, and retrieve the pane containing the grid.
 */
public class CellGridView {
    private ArrayList<ToggleButton> gridButtons;
    private GridPane gridPane;
    private int numRows;
    private int numCols;

    /**
     * Constructs a CellGridView with the specified number of rows, columns, and
     * cell length.
     * 
     * @param numRows    the number of rows in the grid
     * @param numCols    the number of columns in the grid
     * @param cellLength the length of each cell in pixels
     */
    public CellGridView(int numRows, int numCols, int cellLength) {
        this.numCols = numCols;
        this.numRows = numRows;
        gridButtons = new ArrayList<ToggleButton>();
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        initButtons(numRows, numCols, cellLength);
    }

    /**
     * Initializes the toggle buttons in the grid with the specified number of rows,
     * columns, and cell length.
     * 
     * @param numRows    the number of rows in the grid
     * @param numCols    the number of columns in the grid
     * @param cellLength the length of each cell in pixels
     */
    public void initButtons(int numRows, int numCols, int cellLength) {
        this.numRows = numRows;
        this.numCols = numCols;
        gridButtons.clear();
        gridPane.getChildren().clear();

        for (int rowIdx = 0; rowIdx < numRows; rowIdx++) {
            for (int colIdx = 0; colIdx < numCols; colIdx++) {
                ToggleButton button = new ToggleButton();
                button.setMaxHeight(cellLength);
                button.setMinHeight(cellLength);
                button.setPrefHeight(cellLength);

                button.setMaxWidth(cellLength);
                button.setMinWidth(cellLength);
                button.setPrefWidth(cellLength);
                gridButtons.add(button);
                gridPane.add(button, colIdx, rowIdx);
            }
        }
    }

    /**
     * Returns the number of rows in the grid.
     * 
     * @return the number of rows
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Returns the number of columns in the grid.
     * 
     * @return the number of columns
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * Returns the toggle button at the specified row and column indices.
     * 
     * @param rowIdx the row index
     * @param colIdx the column index
     * @return the toggle button at the specified indices
     */
    public ToggleButton getToggleButton(int rowIdx, int colIdx) {
        int l = rowIdx;
        l = l * numCols;
        l = l + colIdx;
        return gridButtons.get(l);
    }

    /**
     * Returns the pane containing the grid.
     * 
     * @return the pane
     */
    public Pane getPane() {
        return gridPane;
    }
}
