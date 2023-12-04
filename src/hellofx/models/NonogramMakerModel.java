package hellofx.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;

/**
 * The NonogramMakerModel class represents a nonogram puzzle grid and provides
 * methods for manipulating and saving the grid.
 */
public class NonogramMakerModel {
    private static char FILLED_CELL_CHAR = '1';
    private static char EMPTY_CELL_CHAR = '0';

    private boolean[] grid;
    private int numRows;
    private int numCols;

    /**
     * Constructs a NonogramMakerModel object with the specified number of rows and
     * columns.
     *
     * @param numRows The number of rows in the nonogram grid.
     * @param numCols The number of columns in the nonogram grid.
     * @throws IllegalArgumentException if the number of rows or columns is less
     *                                  than 1.
     */
    public NonogramMakerModel(int numRows, int numCols) {
        if (numCols < 1 || 1 > numRows) {
            throw new IllegalArgumentException();
        }
        this.numCols = numCols;
        this.numRows = numRows;
        grid = new boolean[numCols * numRows];
    }

    /**
     * Constructs a NonogramMakerModel object by reading the nonogram grid from a
     * file.
     *
     * @param file The file containing the nonogram grid.
     * @throws IOException              if an I/O error occurs while reading the
     *                                  file.
     * @throws IllegalArgumentException if the number of rows or columns is less
     *                                  than 1.
     */
    public NonogramMakerModel(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        if (line != null) {
            String[] numbers = line.split(" ");
            if (numbers.length == 2) {
                this.numRows = Integer.parseInt(numbers[0]);
                this.numCols = Integer.parseInt(numbers[1]);
            }
        }
        grid = new boolean[numRows * numCols];
        if (numCols < 1 || numRows < 1) {
            reader.close();
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < numCols + numRows; i++) {
            reader.readLine();
        }

        int count = 0;
        for (int r = 0; r < numRows; r++) {
            line = reader.readLine();
            for (int c = 0; c < numCols; c++) {
                grid[count] = line.charAt(c) == FILLED_CELL_CHAR;
                count++;
            }
        }
        reader.close();
    }

    /**
     * Constructs a NonogramMakerModel object by reading the nonogram grid from a
     * file.
     *
     * @param filename The name of the file containing the nonogram grid.
     * @throws IOException              if an I/O error occurs while reading the
     *                                  file.
     * @throws IllegalArgumentException if the number of rows or columns is less
     *                                  than 1.
     */
    public NonogramMakerModel(String filename) throws IOException {
        this(new File(filename));
    }

    /**
     * Returns a copy of the nonogram grid.
     *
     * @return A copy of the nonogram grid.
     */
    public boolean[] getGrid() {
        return Arrays.copyOf(grid, grid.length);
    }

    /**
     * Returns the value of the cell at the specified row and column.
     *
     * @param rowIdx The index of the row.
     * @param colIdx The index of the column.
     * @return The value of the cell at the specified row and column.
     */
    public boolean getCell(int rowIdx, int colIdx) {
        int l = rowIdx;
        l = l * numCols;
        l = l + colIdx;
        return grid[l];
    }

    /**
     * Sets the value of the cell at the specified row and column.
     *
     * @param rowIdx The index of the row.
     * @param colIdx The index of the column.
     * @param value  The new value for the cell.
     */
    public void setCell(int rowIdx, int colIdx, boolean value) {
        int l = rowIdx;
        l = l * numCols;
        l = l + colIdx;
        grid[l] = value;
    }

    /**
     * Returns the number of rows in the nonogram grid.
     *
     * @return The number of rows in the nonogram grid.
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Returns the number of columns in the nonogram grid.
     *
     * @return The number of columns in the nonogram grid.
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * Projects the filled cells in the specified array and returns a list of the
     * lengths of the consecutive filled cell groups.
     *
     * @param cells The array representing a row or column of the nonogram grid.
     * @return A list of the lengths of the consecutive filled cell groups.
     */
    public static List<Integer> project(boolean[] cells) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < cells.length; i++) {
            if (cells[i]) {
                int count = 1;
                while (i + 1 < cells.length && cells[i + 1]) {
                    count++;
                    i++;
                }
                list.add(count);
            }
        }
        if (list.size() == 0) {
            list.add(0);
        }
        return list;
    }

    /**
     * Projects the filled cells in the specified row and returns a list of the
     * lengths of the consecutive filled cell groups.
     *
     * @param rowIdx The index of the row.
     * @return A list of the lengths of the consecutive filled cell groups in the
     *         specified row.
     */
    public List<Integer> projectRow(int rowIdx) {
        boolean[] row = new boolean[numCols];
        int i = 0;
        while (i < numCols) {
            row[i] = getCell(rowIdx, i);
            i++;
        }
        return project(row);
    }

    /**
     * Projects the filled cells in the specified column and returns a list of the
     * lengths of the consecutive filled cell groups.
     *
     * @param colIdx The index of the column.
     * @return A list of the lengths of the consecutive filled cell groups in the
     *         specified column.
     */
    public List<Integer> projectCol(int colIdx) {
        boolean[] col = new boolean[numRows];
        int i = 0;
        while (i < numRows) {
            col[i] = getCell(i, colIdx);
            i++;
        }
        return project(col);
    }

    /**
     * Saves the nonogram grid to a file with the specified filename.
     *
     * @param filename The name of the file to save the nonogram grid to.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(toString());
        writer.close();
    }

    /**
     * Returns a string representation of the nonogram grid.
     *
     * @return A string representation of the nonogram grid.
     */
    public String toString() {
        String s = "";

        s += numRows + " " + numCols + "\n";

        for (int r = 0; r < numRows; r++) {
            s += projectRow(r).toString() + "\n";
        }

        for (int c = 0; c < numCols; c++) {
            s += projectCol(c).toString() + "\n";
        }

        for (int r = 0; r < numRows; r++) {
            String row = "";
            for (int c = 0; c < numCols; c++) {
                if (getCell(r, c)) {
                    row += FILLED_CELL_CHAR;
                } else {
                    row += EMPTY_CELL_CHAR;
                }
            }
            s += row + "\n";
        }
        s = s.substring(0, s.length() - 1);
        return s.replaceAll("[\\[\\],/]", "");
    }
}
