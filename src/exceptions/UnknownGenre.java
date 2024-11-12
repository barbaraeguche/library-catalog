package exceptions;

/**
 * this is the unknowngenre exception class. it is thrown a book's genre is not part of the valid book genres.
 */
public class UnknownGenre extends Exception {

    /**
     * default constructor.
     */
    public UnknownGenre() {
        super();
    }

    /**
     * parameterized constructor.
     * @param message the message to be displayed.
     */
    public UnknownGenre(String message) {
        super(message);
    }
}
