package it.unibo.unori.controller.exceptions;

/**
 * Exception thrown when an utility text does not match the correct pattern.
 */
public class CorruptedUtilityFileException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -5424795998960050394L;
    private String corruptedFileName = "requested";

    /**
     * Standard, generic constructor.
     */
    public CorruptedUtilityFileException() {
        super();
    }

    /**
     * Specific constructor.
     * 
     * @param fileName
     *            the name of the corrupted file
     */
    public CorruptedUtilityFileException(final String fileName) {
        super();
        this.corruptedFileName = fileName;
    }

    @Override
    public String toString() {
        return "The utility file " + this.corruptedFileName + " is corrupted or not valid!";
    }
}
