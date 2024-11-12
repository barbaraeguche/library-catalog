package exceptions;

/**
 * this is the missingfield exception class. it is thrown when a book's field is missing.
 */
public class MissingField extends Exception {

    /**
     * default constructor.
     */
    public MissingField() {
        super();
    }

    /**
     * parameterized constructor.
     * @param message the message to be displayed.
     */
    public MissingField(String message) {
        super(message);
    }
}
