package hellofx.interfaces;

import java.io.File;
import java.io.IOException;

/**
 * The Openable interface represents an object that can be opened with a file.
 * Classes that implement this interface must provide an implementation for the
 * open method.
 */
public interface Openable {
    /**
     * Opens the specified file.
     *
     * @param file the file to be opened
     * @throws IOException if an I/O error occurs while opening the file
     */
    void open(File file) throws IOException;
}
