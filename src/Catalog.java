import java.io.*;
import java.util.*;
import exceptions.*;

/**
 * this is the catalog class. it's methods perform error cleanups, serialize,
 * deserialize, and navigates the cleaned library catalog.
 */
public class Catalog {
    Book bookObj = null;
    Scanner mainScanner, scanner;
    Helper helper = new Helper();
    String title, author, price, isbn, genre, year;

    /**
     * this function validates syntax, partitions book records based on genre.
     */
    protected void validateSyntaxAndPartition() {
        try {
            mainScanner = new Scanner(new FileInputStream("filenames.txt")); // read the main file

            while (mainScanner.hasNextLine()) {
                String bookCsv = mainScanner.nextLine(); // read each book{year}.csv file
                if (bookCsv.isEmpty())
                    continue;

                try {
                    scanner = new Scanner(new FileInputStream("textfiles/" + bookCsv)); // read the contents

                    while (scanner.hasNextLine()) {
                        String bookObj = scanner.nextLine().trim();

                        if (bookObj.startsWith("\"")) {
                            int quote1 = bookObj.indexOf("\""), quote2 = bookObj.indexOf("\"", quote1 + 1);

                            title = bookObj.substring(quote1 + 1, quote2);
                            String[] fields = bookObj.substring(quote2 + 2).split(",");

                            author = fields[0];
                            price = fields[1];
                            isbn = fields[2];
                            genre = fields[3];

                            if (fields.length >= 5) {
                                year = fields[4];
                            }

                            if (validateFieldCount(fields, 5, bookObj, bookCsv))
                                continue;
                        } else {
                            String[] fields = bookObj.split(",");

                            title = fields[0];
                            author = fields[1];
                            price = fields[2];
                            isbn = fields[3];
                            genre = fields[4];

                            if (fields.length >= 6) {
                                year = fields[5];
                            }

                            if (validateFieldCount(fields, 6, bookObj, bookCsv))
                                continue;
                        }

                        try {
                            // missing title
                            if (title.isBlank())
                                throw new MissingField("Missing Title.");
                            // missing author
                            else if (author.isBlank())
                                throw new MissingField("Missing Author.");
                            // missing price
                            else if (price.isBlank())
                                throw new MissingField("Missing Price.");
                            // missing isbn
                            else if (isbn.isBlank())
                                throw new MissingField("Missing ISBN.");
                            // missing genre
                            else if (genre.isBlank())
                                throw new MissingField("Missing Genre.");
                            // invalid genre
                            else if (!helper.validSyntaxFiles.containsKey(genre)) {
                                try {
                                    throw new UnknownGenre("Unknown Genre.");
                                } catch (UnknownGenre excep) {
                                    Helper.unknownGenre++;
                                    helper.openWriter(helper.validErrorFiles.get("SYN"),
                                            helper.syntaxError(bookCsv, excep.getMessage(), bookObj));
                                    continue;
                                }
                            }
                            // missing year
                            else if (year.isBlank())
                                throw new MissingField("Missing Year.");

                            helper.openWriter(helper.validSyntaxFiles.get(genre), bookObj); // syntax-free text
                        } catch (MissingField excep) {
                            Helper.missingFields++;
                            helper.openWriter(helper.validErrorFiles.get("SYN"),
                                    helper.syntaxError(bookCsv, excep.getMessage(), bookObj));
                        }
                    }
                    scanner.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Book file not found for reading.");
                }
            }
            mainScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Main file not found for reading.");
        }
    }

    /**
     * this function validates semantics, then serializes each list of book objects
     * into binary files.
     */
    protected void validateSemanticsAndSerialize() {
        List<Book> ccbSerial = new ArrayList<>(), hcbSerial = new ArrayList<>(), mtvSerial = new ArrayList<>(),
                mrbSerial = new ArrayList<>();
        List<Book> nebSerial = new ArrayList<>(), otrSerial = new ArrayList<>(), ssmSerial = new ArrayList<>(),
                tpaSerial = new ArrayList<>();

        for (String value : helper.validSyntaxFiles.values()) {
            try {
                scanner = new Scanner(new FileInputStream(value));

                while (scanner.hasNextLine()) {
                    String genreText = scanner.nextLine().trim();

                    if (genreText.startsWith("\"")) {
                        int quote1 = genreText.indexOf("\""), quote2 = genreText.indexOf("\"", quote1 + 1);

                        title = genreText.substring(quote1 + 1, quote2);
                        String[] fields = genreText.substring(quote2 + 2).split(",");

                        author = fields[0];
                        price = fields[1];
                        isbn = fields[2];
                        genre = fields[3];
                        year = fields[4];
                    } else {
                        String[] fields = genreText.split(",");

                        title = fields[0];
                        author = fields[1];
                        price = fields[2];
                        isbn = fields[3];
                        genre = fields[4];
                        year = fields[5];
                    }

                    try {
                        bookObj = new Book(title, author, Double.parseDouble(price), isbn, genre,
                                Integer.parseInt(year));
                    } catch (BadPrice | BadIsbn10 | BadIsbn13 | BadIsbnLength | UnknownGenre | BadYear excep) {
                        if (excep instanceof BadPrice)
                            Helper.badPrice++;
                        else if (excep instanceof BadIsbn10)
                            Helper.badIsbn10++;
                        else if (excep instanceof BadIsbn13)
                            Helper.badIsbn13++;
                        else if (excep instanceof BadIsbnLength)
                            Helper.badIsbnLength++;
                        // else if(excep instanceof UnknownGenre) helper.unknownGenre++;
                        else if (excep instanceof BadYear)
                            Helper.badYear++;

                        // log the semantic error
                        helper.openWriter(helper.validErrorFiles.get("SEM"),
                                helper.semanticError(value.substring(11), excep.getMessage(), genreText));
                        continue;
                    }

                    // add all syntax-free and semantic-free book objects in their respective genre
                    // list
                    switch (value) {
                        case "genrefiles/Cartoons_Comics_Books.csv.txt" -> ccbSerial.add(bookObj);
                        case "genrefiles/Hobbies_Collectibles_Books.csv.txt" -> hcbSerial.add(bookObj);
                        case "genrefiles/Movies_TV.csv.txt" -> mtvSerial.add(bookObj);
                        case "genrefiles/Music_Radio_Books.csv.txt" -> mrbSerial.add(bookObj);
                        case "genrefiles/Nostalgia_Eclectic_Books.csv.txt" -> nebSerial.add(bookObj);
                        case "genrefiles/Old_Time_Radio.csv.txt" -> otrSerial.add(bookObj);
                        case "genrefiles/Sports_Sports_Memorabilia.csv.txt" -> ssmSerial.add(bookObj);
                        case "genrefiles/Trains_Planes_Automobiles.csv.txt" -> tpaSerial.add(bookObj);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found for reading.");
            }
        }

        // serialize all lists
        for (String key : helper.validSemanticFiles.keySet()) {
            switch (key) {
                case "CCB" -> helper.serializeAll(helper.validSemanticFiles.get(key), ccbSerial);
                case "HCB" -> helper.serializeAll(helper.validSemanticFiles.get(key), hcbSerial);
                case "MTV" -> helper.serializeAll(helper.validSemanticFiles.get(key), mtvSerial);
                case "MRB" -> helper.serializeAll(helper.validSemanticFiles.get(key), mrbSerial);
                case "NEB" -> helper.serializeAll(helper.validSemanticFiles.get(key), nebSerial);
                case "OTR" -> helper.serializeAll(helper.validSemanticFiles.get(key), otrSerial);
                case "SSM" -> helper.serializeAll(helper.validSemanticFiles.get(key), ssmSerial);
                case "TPA" -> helper.serializeAll(helper.validSemanticFiles.get(key), tpaSerial);
            }
        }
    }

    /**
     * this function deserializes binary files into list of book objects partitioned
     * by genre, then provides an interactive program to navigate the library
     * catalog.
     */
    protected void deserializeAndNavigateCatalog() {
        List<Book> ccbSerial = new ArrayList<>(), hcbSerial = new ArrayList<>(), mtvSerial = new ArrayList<>(),
                mrbSerial = new ArrayList<>();
        List<Book> nebSerial = new ArrayList<>(), otrSerial = new ArrayList<>(), ssmSerial = new ArrayList<>(),
                tpaSerial = new ArrayList<>();

        Map<String, List<Book>> bookLists = new HashMap<>();
        String selectedFile = "";

        // deserialize all lists
        for (String keys : helper.validSemanticFiles.keySet()) {
            switch (keys) {
                case "CCB" -> ccbSerial = helper.deserializeAll(helper.validSemanticFiles.get(keys));
                case "HCB" -> hcbSerial = helper.deserializeAll(helper.validSemanticFiles.get(keys));
                case "MTV" -> mtvSerial = helper.deserializeAll(helper.validSemanticFiles.get(keys));
                case "MRB" -> mrbSerial = helper.deserializeAll(helper.validSemanticFiles.get(keys));
                case "NEB" -> nebSerial = helper.deserializeAll(helper.validSemanticFiles.get(keys));
                case "OTR" -> otrSerial = helper.deserializeAll(helper.validSemanticFiles.get(keys));
                case "SSM" -> ssmSerial = helper.deserializeAll(helper.validSemanticFiles.get(keys));
                case "TPA" -> tpaSerial = helper.deserializeAll(helper.validSemanticFiles.get(keys));
            }
        }
        Helper.booksCreated = ccbSerial.size() + hcbSerial.size() + mtvSerial.size() + mrbSerial.size()
                + nebSerial.size() + otrSerial.size() + ssmSerial.size() + tpaSerial.size();

        bookLists.put("Cartoons_Comics_Books.csv.ser", ccbSerial);
        bookLists.put("Hobbies_Collectibles_Books.csv.ser", hcbSerial);
        bookLists.put("Movies_TV.csv.ser", mtvSerial);
        bookLists.put("Music_Radio_Books.csv.ser", mrbSerial);
        bookLists.put("Nostalgia_Eclectic_Books.csv.ser", nebSerial);
        bookLists.put("Old_Time_Radio.csv.ser", otrSerial);
        bookLists.put("Sports_Sports_Memorabilia.csv.ser", ssmSerial);
        bookLists.put("Trains_Planes_Automobiles.csv.ser", tpaSerial);

        // prints the welcome message
        System.out.print("""

            ~~~~~~~~~~~~~~~~~~~ Welcome to Library Catalog Processor ~~~~~~~~~~~~~~~~~~~
            I will be helping you navigate through book titles, categorized by genre, as well as providing a facility for identifying and removing invalid book records :)
            """);

        while(true) {
            mainScanner = new Scanner(System.in);
                
            System.out.printf("""

                -----------------------------
                          Main Menu
                -----------------------------
                 v  View the selected file: %s
                 s  Select a file to view
                 x  Exit
                -----------------------------
                """, selectedFile);

            //prompt the user to enter a choice from the menu above
            System.out.print("Enter your choice: ");
            String prompt = mainScanner.next().trim().toLowerCase();

            // --------------------------------------------------------------------------------------------------
            if(prompt.equals("v")) {
                if(selectedFile.isEmpty()) {
                    System.out.println("No file selected.");
                    continue;
                }

                System.out.printf("viewing: %s%n", selectedFile);
                while(true) {
                    try {
                        System.out.print("\nEnter an integer number: ");
                        int num = scanner.nextInt();

                        if(num == 0) {
                            System.out.println("Viewing ended.");
                            break;
                        }

                        for(String key : bookLists.keySet()) {
                            if(key.equals(selectedFile.substring(0, selectedFile.indexOf(" ")))) {
                                helper.validateSearch(num, bookLists.get(key));
                            }
                        }
                    } catch(InputMismatchException e) {
                        System.out.println("Enter an integer.");
                    }
                }
            }

            // --------------------------------------------------------------------------------------------------
            else if(prompt.equals("s")) {
                scanner = new Scanner(System.in);
                
                do {
                    System.out.printf("""

                        ------------------------------
                                File Sub-Menu
                        ------------------------------
                        1  Cartoons_Comics_Books.csv.ser         (%d records)
                        2  Hobbies_Collectibles_Books.csv.ser    (%d records)
                        3  Movies_TV.csv.ser                     (%d records)
                        4  Music_Radio_Books.csv.ser             (%d records)
                        5  Nostalgia_Eclectic_Books.csv.ser      (%d records)
                        6  Old_Time_Radio.csv.ser                (%d records)
                        7  Sports_Sports_Memorabilia.csv.ser     (%d records)
                        8  Trains_Planes_Automobiles.csv.ser     (%d records)
                        9  Exit
                        ------------------------------
                        """, ccbSerial.size(), hcbSerial.size(), mtvSerial.size(), mrbSerial.size(), nebSerial.size(),
                        otrSerial.size(), ssmSerial.size(), tpaSerial.size());

                    try {
                        //prompt the user to enter a choice from the sub-menu above
                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();

                        selectedFile = switch(choice) {
                            case 1 -> String.format("Cartoons_Comics_Books.csv.ser (%d records)", ccbSerial.size());
                            case 2 -> String.format("Hobbies_Collectibles_Books.csv.ser (%d records)", hcbSerial.size());
                            case 3 -> String.format("Movies_TV.csv.ser (%d records)", mtvSerial.size());
                            case 4 -> String.format("Music_Radio_Books.csv.ser (%d records)", mrbSerial.size());
                            case 5 -> String.format("Nostalgia_Eclectic_Books.csv.ser (%d records)", nebSerial.size());
                            case 6 -> String.format("Old_Time_Radio.csv.ser (%d records)", otrSerial.size());
                            case 7 -> String.format("Sports_Sports_Memorabilia.csv.ser (%d records)", ssmSerial.size());
                            case 8 -> String.format("Trains_Planes_Automobiles.csv.ser (%d records)", tpaSerial.size());
                            case 9 -> {
                                System.out.println("File sub-menu exited.");
                                yield "";
                            }
                            default -> {
                                System.out.println("Sorry that is not a valid choice, try again.");
                                yield "none";
                            }
                        };
                    } catch(InputMismatchException e) {
                        System.out.println("Enter an integer.");
                        break;
                    }
                } while("none".equals(selectedFile));
            }

            // --------------------------------------------------------------------------------------------------
            else if(prompt.equals("x")) {
                System.out.println("Interactive main menu terminated.\n");
                break;
            }

            // --------------------------------------------------------------------------------------------------
            else {
                System.out.println("Sorry that is not a valid choice, try again.");
            }
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~ helper functions ~~~~~~~~~~~~~~~~~~~~~~~~ //
    /**
     * this function handles the validation of field counts and logs errors if
     * necessary.
     * 
     * @param fields         the array of fields to validate.
     * @param expectedLength the expected number of fields.
     * @param bookObj        the original string of fields.
     * @param bookCsv        the .csv file name where the field was found.
     * @throws FileNotFoundException if the file can not be found.
     */
    private boolean validateFieldCount(String[] fields, int expectedLength, String bookObj, String bookCsv) throws FileNotFoundException {
        int fieldCount = fields.length;

        if (fieldCount != expectedLength) {
            String exceptionMsg = fieldCount > expectedLength ? "Too Many Fields." : "Too Few Fields.";

            try {
                if (fieldCount > expectedLength) {
                    Helper.tooManyFields++;
                    throw new TooManyFields(exceptionMsg);
                } else {
                    Helper.tooFewFields++;
                    throw new TooFewFields(exceptionMsg);
                }
            } catch (TooManyFields | TooFewFields excep) {
                helper.openWriter(helper.validErrorFiles.get("SYN"),
                        helper.syntaxError(bookCsv, excep.getMessage(), bookObj));
                return true;
            }
        }
        return false;
    }
}
