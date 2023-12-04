package hellofx.handlers;

import hellofx.models.NonogramMakerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

/**
 * The ToggleButtonEventHandler class is responsible for handling the action
 * events of toggle buttons in the NonogramMaker application.
 */
public class ToggleButtonEventHandler implements EventHandler<ActionEvent> {
    private NonogramMakerModel model;
    private int rowIdx;
    private int colIdx;

    /**
     * Constructs a ToggleButtonEventHandler with the specified NonogramMakerModel,
     * row index, and column index.
     * 
     * @param rowIdx The row index of the toggle button.
     * @param colIdx The column index of the toggle button.
     * @param model  The NonogramMakerModel to update.
     */
    public ToggleButtonEventHandler(NonogramMakerModel model, int rowIdx, int colIdx) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
        this.model = model;
    }

    /**
     * Handles the action event triggered by the toggle button.
     * 
     * @param event Action event.
     */
    public void handle(ActionEvent event) {
        model.setCell(rowIdx, colIdx, ((ToggleButton) event.getSource()).isSelected());
    }
}
