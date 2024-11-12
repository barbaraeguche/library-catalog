package exceptions;

/**
 * this is the toofewfields exception class. it is thrown a book has too few fields ie when the fields of such book is less than 6.
 */
public class TooFewFields extends Exception {

    /**
     * default constructor.
     */
    public TooFewFields() {
        super();
    }

    /**
     * parameterized constructor.
     * @param message the message to be displayed.
     */
    public TooFewFields(String message) {
        super(message);
    }
}
