package exceptions;

/**
 * this is the badisbn10 exception class. it is thrown when an isbn of length 10 is not a multiple of 11.
 */
public class BadIsbn10 extends Exception {

    /**
     * default constructor.
     */
    public BadIsbn10() {
        super();
    }

    /**
     * parameterized constructor.
     * @param message the message to be displayed.
     */
    public BadIsbn10(String message) {
        super(message);
    }
}
