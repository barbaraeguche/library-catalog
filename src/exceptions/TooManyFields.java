package exceptions;

/**
 * this is the toomanyfields exception class. it is thrown a book has too many fields ie when the fields of such book is greater than 6.
 */
public class TooManyFields extends Exception {

    /**
     * default constructor.
     */
    public TooManyFields() {
        super();
    }

    /**
     * parameterized constructor.
     * @param message the message to be displayed.
     */
    public TooManyFields(String message) {
        super(message);
    }
}
