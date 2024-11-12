package exceptions;

/**
 * this is the badyear exception class. it is thrown when a book's publication year doesn't fall between the range of [1995, 2010].
 */
public class BadYear extends Exception {

    /**
     * default constructor.
     */
    public BadYear() {
        super();
    }

    /**
     * parameterized constructor.
     * @param message the message to be displayed.
     */
    public BadYear(String message) {
        super(message);
    }
}
