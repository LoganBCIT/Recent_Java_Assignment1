package ca.bcit.comp2601.assignment1;

/**
 * Models an IllegalPersonException
 *
 * @author Logan Dutton-Anderson
 * @version 1.0
 */
public class IllegalPersonException extends RuntimeException {
    public IllegalPersonException(final String message) {
        super(message);
    }
}
