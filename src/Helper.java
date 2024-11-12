import java.util.*;
import java.io.*;

/**
 * this is the helper class.
 */
public class Helper {
    //variables for tracking errors
    protected static int tooFewFields = 0, tooManyFields = 0, missingFields = 0, unknownGenre = 0;
    protected static int badPrice = 0, badIsbn10 = 0, badIsbn13 = 0, badIsbnLength = 0, badYear = 0;
    protected static int current = 0, booksCreated = 0;
    protected static String genreFiles = "genrefiles/", serializedFiles = "serialized/", errorFiles = "errorfiles/";
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    protected final Map<String, String> validSyntaxFiles = new HashMap<>(); {
        validSyntaxFiles.put("CCB", genreFiles + "Cartoons_Comics_Books.csv.txt");
        validSyntaxFiles.put("HCB", genreFiles + "Hobbies_Collectibles_Books.csv.txt");
        validSyntaxFiles.put("MTV", genreFiles + "Movies_TV.csv.txt");
        validSyntaxFiles.put("MRB", genreFiles + "Music_Radio_Books.csv.txt");
        validSyntaxFiles.put("NEB", genreFiles + "Nostalgia_Eclectic_Books.csv.txt");
        validSyntaxFiles.put("OTR", genreFiles + "Old_Time_Radio.csv.txt");
        validSyntaxFiles.put("SSM", genreFiles + "Sports_Sports_Memorabilia.csv.txt");
        validSyntaxFiles.put("TPA", genreFiles + "Trains_Planes_Automobiles.csv.txt");
    }
    
    protected final Map<String, String> validSemanticFiles = new HashMap<>(); {
        validSemanticFiles.put("CCB", serializedFiles + "Cartoons_Comics_Books.csv.ser");
        validSemanticFiles.put("HCB", serializedFiles + "Hobbies_Collectibles_Books.csv.ser");
        validSemanticFiles.put("MTV", serializedFiles + "Movies_TV.csv.ser");
        validSemanticFiles.put("MRB", serializedFiles + "Music_Radio_Books.csv.ser");
        validSemanticFiles.put("NEB", serializedFiles + "Nostalgia_Eclectic_Books.csv.ser");
        validSemanticFiles.put("OTR", serializedFiles + "Old_Time_Radio.csv.ser");
        validSemanticFiles.put("SSM", serializedFiles + "Sports_Sports_Memorabilia.csv.ser");
        validSemanticFiles.put("TPA", serializedFiles + "Trains_Planes_Automobiles.csv.ser");
    }
    
    protected final Map<String, String> validErrorFiles = new HashMap<>(); {
        validErrorFiles.put("SYN", errorFiles + "syntaxError.txt");
        validErrorFiles.put("SEM", errorFiles + "semanticError.txt");
    }
    
    protected static final List<Map<String, String>> toDelete = new ArrayList<>(); {
        toDelete.add(validSyntaxFiles);
        toDelete.add(validSemanticFiles);
        toDelete.add(validErrorFiles);
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    /**
     * this method writes the syntax error message to the specified file.
     * @param file the file where the syntax error was caught.
     * @param error the syntax error message.
     * @param text the record with the error.
     * @return a formatted string describing the syntax error.
     */
    protected String syntaxError(String file, String error, String text) {
        return String.format("File: %s%n====================%nError: %s%nRecord: %s%n%n", file, error, text);
    }
    /**
     * this method writes the semantic error message to the specified file.
     * @param file the file where the semantic error was caught.
     * @param error the semantic error message.
     * @param text the record with the error.
     * @return a formatted string describing the semantic error.
     */
    protected String semanticError(String file, String error, String text) {
        return String.format("File: %s%n====================%nError: %s%nRecord: %s%n%n", file, error, text);
    }

    /**
     * this method opens the stream for all the file objects.
     * @param file the file to write to.
     * @param toWrite the message to write.
     */
    protected void openWriter(String file, String toWrite) {
        File outputFile = new File(file);
        File directory = outputFile.getParentFile();

        //ensure the directory exists; create it if it doesn't
        if(!directory.exists()) {
            directory.mkdirs();
        }

        //create the file if it doesn't exist
        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream(outputFile, true))) {
            printWriter.println(toWrite);
            printWriter.flush();
        } catch(FileNotFoundException e) {
            System.out.printf("Failed to create or write to file: %s.%n", outputFile.getPath());
        }
    }

    /**
     * this method serializes all the textfiles.
     * @param file the file where the serialized text goes.
     * @param array an array of book objects to serialize.
     */
    protected void serializeAll(String file, List<Book> array) {
        File outputFile = new File(file);
        File directory = outputFile.getParentFile();

        //ensure the directory exists; create it if it doesn't
        if(!directory.exists()) {
            directory.mkdirs();
        }

        try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            outStream.writeObject(array);
            outStream.flush();
        } catch(IOException io) {
            System.err.printf("The %s arraylist was not serialized.%n", array);
        }
    }
    /**
     * this method deserializes all the textfiles.
     * @param file the file containing the serialized book objects.
     * @return a list containing the deserialized book objects.
     */
    @SuppressWarnings("unchecked")
    protected List<Book> deserializeAll(String file) {
        List<Book> bookArray = new ArrayList<>();
        
        try(ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file))) {
            bookArray = (List<Book>)inStream.readObject();
            inStream.close();
        } catch(ClassNotFoundException cls) {
            System.err.printf("Class not found: %s.%n", cls.getMessage());
        } catch(IOException io) {
            System.err.printf("Array not deserialized: %s.%n", io.getMessage());
        }

        return bookArray;
    }

    /**
     * this method validates user's input and executes the program accordingly.
     * @param window the user input.
     * @param bookArray an arraylist containing the deserialized book objects.
     */
    protected void validateSearch(int window, List<Book> bookArray) {
        if(window > 0) {
            int endIndex = Math.min(current + window, bookArray.size());

            for(int idx = current; idx < endIndex; idx++) {
                System.out.print(bookArray.get(idx));
            }
            
            //update current position to the last index viewed
            current = endIndex - 1;
            if(current + 1 >= bookArray.size()) {
                System.out.println("~~ You have reached the end of this catalog.");
            }
        } else {
            int startIndex = Math.max(0, current + window + 1);
        
            if(startIndex == 0) {
                System.out.println("~~ You are at the beginning of this catalog.");
            }
            
            for(int idx = startIndex; idx <= current; idx++) {
                System.out.print(bookArray.get(idx));
            }
            
            //update current position to the first index viewed
            current = startIndex;
        }
    }

    /**
     * this method deletes all the textfiles used in this program.
     */
    protected static void deleteFiles() {
        for(Map<String, String> map: toDelete) {
            for(String path: map.values()) {
                (new File(path)).delete();
            }
        }
    }
}
