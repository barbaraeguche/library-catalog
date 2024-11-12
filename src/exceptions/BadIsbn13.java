package exceptions;

/**
 * this is the badisbn13 exception class. it is thrown when an isbn of length 13 is not a multiple of 10.
 */
public class BadIsbn13 extends Exception {

    /**
     * default constructor.
     */
    public BadIsbn13() {
        super();
    }

    /**
     * parameterized constructor.
     * @param message the message to be displayed.
     */
    public BadIsbn13(String message) {
        super(message);
    }
}
