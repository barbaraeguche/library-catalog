package exceptions;

/**
 * this is the badprice exception class. it is thrown when a book's price is negative.
 */
public class BadPrice extends Exception {

    /**
     * default constructor.
     */
    public BadPrice() {
        super();
    }

    /**
     * parameterized constructor.
     * @param message the message to be displayed.
     */
    public BadPrice(String message) {
        super(message);
    }
}
