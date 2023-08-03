package ca.bcit.comp2601.assignment1;

/**
 * Models a writable functional Interface
 *
 * @author Logan Dutton-Anderson
 * @version 1.0
 */
@FunctionalInterface
public interface Writeable {
    public void printData(String s, int min, int max);
}
