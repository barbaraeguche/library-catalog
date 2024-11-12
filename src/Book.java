import java.io.Serializable;
import java.util.Arrays;
import exceptions.*;

/**
 * this is the book class which implements serializable.
 */
public class Book implements Serializable {
    //instance variables
    private String title, author, isbn, genre; 
    private double price; 
    private int year;

    /**
     * default constructor.
     */
    public Book() {}

    /**
     * parameterized constructor to initialize the book object.
     * @param title the book's title.
     * @param author the book's author.
     * @param price the book's price.
     * @param isbn the book's isbn.
     * @param genre the book's genre.
     * @param year the book's publishing year.
     * @throws BadPrice when the price is not positive.
     * @throws BadIsbn10 when a 10-digit isbn is not valid.
     * @throws BadIsbn13 when a 13-digit isbn is not valid.
     * @throws BadIsbnLength when an isbn length is neither 10-digit nor 13-digit.
     * @throws UnknownGenre when the genre is not among the valid specified genre.
     * @throws BadYear when the year is not within the range of [1995, 2010].
     */
    public Book(String title, String author, double price, String isbn, String genre, int year) throws BadPrice, BadIsbn10, BadIsbn13, BadIsbnLength, UnknownGenre, BadYear {
        this.setTitle(title);
        this.setAuthor(author);
        this.setPrice(price);
        this.setIsbn(isbn);
        this.setGenre(genre);
        this.setYear(year);
    }

    /**
     * copy constructor.
     * @throws BadPrice when the price is not positive.
     * @throws BadIsbn10 when a 10-digit isbn is not valid.
     * @throws BadIsbn13 when a 13-digit isbn is not valid.
     * @throws BadIsbnLength when an isbn length is neither 10-digit nor 13-digit.
     * @throws UnknownGenre when the genre is not among the valid specified genre.
     * @throws BadYear when the year is not within the range of [1995, 2010].
     */
    public Book(Book book) throws BadPrice, BadIsbn10, BadIsbn13, BadIsbnLength, UnknownGenre, BadYear {
        this.setTitle(book.getTitle());
        this.setAuthor(book.getAuthor());
        this.setPrice(book.getPrice());
        this.setIsbn(book.getIsbn());
        this.setGenre(book.getGenre());
        this.setYear(book.getYear());
    }

    /**
     * an accessor method.
     * @return the book's title.
     */
    public String getTitle() {
        return this.title;
    }
    /**
     * an accessor method.
     * @return the book's author.
     */
    public String getAuthor() {
        return this.author;
    }
    /**
     * an accessor method.
     * @return the book's price.
     */
    public double getPrice() {
        return this.price;
    }
    /**
     * an accessor method.
     * @return the book's isbn.
     */
    public String getIsbn() {
        return this.isbn;
    }
    /**
     * an accessor method.
     * @return the book's genre.
     */
    public String getGenre() {
        return this.genre;
    }
    /**
     * an accessor method.
     * @return the book's year.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * a mutator method.
     * @param title the book's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * a mutator method.
     * @param title the book's author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * a mutator method.
     * @param title the book's price.
     * @throws BadPrice when the price is not positive.
     */
    public void setPrice(double price) throws BadPrice {
        if(price < 0) throw new BadPrice("Bad Price.");
        else this.price = price;
    }
    /**
     * a mutator method.
     * @param title the book's isbn.
     * @throws BadIsbn10 when a 10-digit isbn is not valid.
     * @throws BadIsbn13 when a 13-digit isbn is not valid.
     * @throws BadIsbnLength when an isbn length is neither 10-digit nor 13-digit.
     */
    public void setIsbn(String isbn) throws BadIsbn10, BadIsbn13, BadIsbnLength {
        this.isbn = validateIsbn1013X(isbn);
    }
    /**
     * a mutator method.
     * @param title the book's genre.
     * @throws UnknownGenre when the genre is not among the valid specified genre.
     */
    public void setGenre(String genre) throws UnknownGenre {
        String[] validGenre = { "CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA" };
        if(!Arrays.asList(validGenre).contains(genre.toUpperCase())) throw new UnknownGenre("Unknown Genre");
        else this.genre = genre;
    }
    /**
     * a mutator method.
     * @param title the book's year.
     * @throws BadYear when the year is not within the range of [1995, 2010].
     */
    public void setYear(int year) throws BadYear {
        if(year < 1995 || year > 2010) throw new BadYear("Bad Year.");
        else this.year = year;
    }

    /**
     * the equals method.
     * @param obj the object being compared.
     * @return true if two objects are of the book class and have equal values for all class attributes, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()) return false;

        Book book = (Book)obj;
        return title.equals(book.title) && author.equals(book.author) && Double.compare(price, book.price) == 0 && isbn.equals(book.isbn) && genre.equals(book.genre) && year == book.year;
    }

    /**
     * a string method that returns a representation of the book objects.
     * @return a string representation of a book object.
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %.2f, %s, %s, %d.%n", this.title, this.author, this.price, this.isbn, this.genre, this.year);
    }

    /**
     * this function validates a book object's isbn field.
     * @param isbn the book's isbn.
     * @return a validated isbn number.
     * @throws BadIsbn10 when a 10-digit isbn is not valid.
     * @throws BadIsbn13 when a 13-digit isbn is not valid.
     * @throws BadIsbnLength when an isbn length is neither 10-digit nor 13-digit.
     */
    private String validateIsbn1013X(String isbn) throws BadIsbn10, BadIsbn13, BadIsbnLength {
        int sum = 0;

        if(isbn.length() == 10) {            
            if(isbn.endsWith("X")) throw new BadIsbn10("Invalid ISBN-10.");

            for(int i = 0; i < isbn.length(); i++) {
                sum += (10 - i) * (int)isbn.charAt(i);
            }
            
            if(sum % 11 != 0) throw new BadIsbn10("Invalid ISBN-10.");
        } else if(isbn.length() == 13) {
            if(isbn.endsWith("X")) throw new BadIsbn13("Invalid ISBN-13.");
            
            for(int i = 0; i < isbn.length(); i++) {
               if(i % 2 != 0){
                   sum += 3 * (int)isbn.charAt(i);
                   continue;
               }
               sum += (int)isbn.charAt(i);
            }
            
            if(sum % 10 != 0) throw new BadIsbn13("Invalid ISBN-13.");
        } else throw new BadIsbnLength("ISBN length not a 10 or 13 digit.");
        
        return isbn;
    }
}
