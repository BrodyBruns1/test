package hellofx.interfaces;

import java.io.IOException;

/**
 * The Saveable interface represents an object that can be saved to a file.
 * Implementing classes must provide a way to save the object to a specified
 * filename.
 */
public interface Saveable {
    /**
     * Saves the object to a file with the specified filename.
     *
     * @param filename the name of the file to save the object to
     * @throws IOException if an I/O error occurs while saving the object
     */
    void save(String filename) throws IOException;
}
