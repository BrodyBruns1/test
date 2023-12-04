package hellofx;

import java.util.List;

import hellofx.presenters.NonogramMakerPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The main class of the application.
 * This class extends the Application class and is responsible for launching the
 * application.
 */
public class Main extends Application {

    private static int IDX_NUM_ROWS = 0;
    private static int IDX_NUM_COLS = 1;
    private static int IDX_CELL_SIZE = 2;

    /**
     * This method is called when the application is launched.
     * It sets up the necessary components and starts the application.
     *
     * @param primaryStage The primary stage for the application.
     * @throws Exception If an exception occurs during the application startup.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        List<String> arg = getParameters().getUnnamed();
        int cellSize = Integer.parseInt(arg.get(IDX_CELL_SIZE));
        int numRows = Integer.parseInt(arg.get(IDX_NUM_ROWS));
        int numCols = Integer.parseInt(arg.get(IDX_NUM_COLS));

        NonogramMakerPresenter presenter = new NonogramMakerPresenter(numRows, numCols, cellSize);
        Pane pane = presenter.getPane();
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Simple Nonogram Maker");
        scene.getStylesheets().add("hellofx/style.css");

        primaryStage.show();
    }

    /**
     * The main method of the HelloFX application.
     * It launches the JavaFX application by calling the launch method.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
