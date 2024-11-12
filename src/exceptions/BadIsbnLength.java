package exceptions;

/**
 * this is the badisbnlength exception class. it is thrown when an isbn length is neither a 10-digit nor a 13-digit.
 */
public class BadIsbnLength extends Exception {

    /**
     * default constructor.
     */
    public BadIsbnLength() {
        super();
    }

    /**
     * parameterized constructor.
     * @param message the message to be displayed.
     */
    public BadIsbnLength(String message) {
        super(message);
    }
}
