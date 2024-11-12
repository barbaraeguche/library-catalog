import java.io.*;
import java.util.*;
import exceptions.*;

/**
 * this is the catalog class. it's methods perform error cleanups, serialize, deserialize, and navigates the cleaned library catalog.
 */
public class Catalog {
    Helper helper;

    /**
     * this function validates syntax, partitions book records based on genre.
     */
    protected void validateSyntaxAndPartition() {
        String title, author, price, isbn, genre, year = "";
        Scanner scanner;

        try {
            scanner = new Scanner(new FileInputStream("filenames.txt"));  //read the main file
            
            while(scanner.hasNextLine()) {
                String bookCsv = scanner.nextLine(); //read each book{year}.csv file
                if(bookCsv.isEmpty()) continue;

                try {
                    scanner = new Scanner(new FileInputStream("textfiles/" + bookCsv)); //read the contents

                    while(scanner.hasNextLine()) {
                        String bookObj = scanner.nextLine().trim();

                        if(bookObj.startsWith("\"")) {
                            int firstQuote = bookObj.indexOf("\""), secondQuote = bookObj.indexOf("\"", firstQuote + 1);

                            bookObj = bookObj.substring(firstQuote + 1, secondQuote) + bookObj.substring(secondQuote + 1);
                            System.out.println(bookObj);
                        }











                    //     if(bookObj.startsWith("\"")) {
                    //         int firstQuote = bookObj.indexOf("\""), secondQuote = bookObj.indexOf("\"", firstQuote + 1);
                    //         title = bookObj.substring(firstQuote + 1, secondQuote);



                    //         String[] withoutTitle = bookObj.substring(secondQuote + 2).split(",");
                    //         if(withoutTitle.length < 5) {
                    //             author = withoutTitle[0]; price = withoutTitle[1]; isbn = withoutTitle[2]; genre = withoutTitle[3];
                    //         } else {
                    //             author = withoutTitle[0]; price = withoutTitle[1]; isbn = withoutTitle[2]; genre = withoutTitle[3]; year = withoutTitle[4];
                    //         }

                    //         if(withoutTitle.length > 5 || bookObj.endsWith(",")) { // too many fields
                    //             try {
                    //                 throw new TooManyFields("Too Many Fields");
                    //             } catch (TooManyFields e) {
                    //                 tooManyFields++;
                    //                 openPrintWriter(errorFiles.get("SYN"), synError(bookCsv, String.valueOf(e), bookObj));
                    //                 continue;
                    //             }
                    //         } else if(withoutTitle.length < 5) { // too few fields
                    //             try {
                    //                 throw new TooFewFields("Too Few Fields");
                    //             } catch (TooFewFields e) {
                    //                 tooFewFields++;
                    //                 openPrintWriter(errorFiles.get("SYN"), synError(bookCsv, String.valueOf(e), bookObj));
                    //                 continue;
                    //             }
                    //         }
                    //     } else {
                    //         String[] withTitle = bookObj.split(",");
                    //         if(withTitle.length < 6) {
                    //             title = withTitle[0]; author = withTitle[1]; price = withTitle[2]; isbn = withTitle[3]; genre = withTitle[4];
                    //         } else {
                    //             title = withTitle[0]; author = withTitle[1]; price = withTitle[2]; isbn = withTitle[3]; genre = withTitle[4]; year = withTitle[5];
                    //         }

                    //         if(withTitle.length > 6 || bookObj.endsWith(",")) { // too many fields
                    //             try {
                    //                 throw new TooManyFields("Too Many Fields");
                    //             } catch (TooManyFields e) {
                    //                 tooManyFields++;
                    //                 openPrintWriter(errorFiles.get("SYN"), synError(bookCsv, String.valueOf(e), bookObj));
                    //                 continue;
                    //             }
                    //         } else if(withTitle.length < 6) { // too few fields
                    //             try {
                    //                 throw new TooFewFields("Too Few Fields");
                    //             } catch (TooFewFields e) {
                    //                 tooFewFields++;
                    //                 openPrintWriter(errorFiles.get("SYN"), synError(bookCsv, String.valueOf(e), bookObj));
                    //                 continue;
                    //             }
                    //         }
                    //     }

                    //     try {
                    //         if(title.isBlank()) { //missing title
                    //             throw new MissingField("Missing Title");
                    //         } else if(author.isBlank()) { //missing author
                    //             throw new MissingField("Missing Author");
                    //         } else if(price.isBlank()) { //missing price
                    //             throw new MissingField("Missing Price");
                    //         } else if(isbn.isBlank()) { //missing isbn
                    //             throw new MissingField("Missing ISBN");
                    //         } else if(genre.isBlank()) { //missing genre
                    //             throw new MissingField("Missing Genre");
                    //         } else if(!validSyntaxFiles.containsKey(genre)) { //invalid genre
                    //             try {
                    //                 throw new UnknownGenre("Unknown Genre");
                    //             } catch (UnknownGenre e) {
                    //                 unknownGenre++;
                    //                 openPrintWriter(errorFiles.get("SYN"), synError(bookCsv, String.valueOf(e), bookObj));
                    //                 continue;
                    //             }
                    //         } else if(year.isBlank()) { //missing genre
                    //             throw new MissingField("Missing Year");
                    //         }

                    //         openPrintWriter(validSyntaxFiles.get(genre), bookObj); // syntax-free text
                    //     } catch (MissingField e) {
                    //         missingFields++;
                    //         openPrintWriter(errorFiles.get("SYN"), synError(bookCsv, String.valueOf(e), bookObj));
                    //     }



















                    }
                } catch(FileNotFoundException e) {
                    System.out.println("Book file not found for reading.");
                }
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("Main file not found for reading.");
        }
    }

    /**
     * this function validates semantics, then serializes each list of book objects into binary files.
     */
    protected void validateSemanticsAndSerialize() {

    }

    /**
     * this function deserializes binary files into list of book objects partitioned by genre, then provides an interactive program to navigate the library catalog.
     */
    protected void deserializeAndNavigateCatalog() {

    }
}























/**
//      * This function validates semantics, reads each genre files into lists of Book objects, then serializes each lists into binary files.
//      */
//     private static void do_part2() {
//         ArrayList<Book> ccbSerial = new ArrayList<>(), hcbSerial = new ArrayList<>(), mtvSerial = new ArrayList<>(), mrbSerial = new ArrayList<>(),
//                 nebSerial = new ArrayList<>(), otrSerial = new ArrayList<>(), ssmSerial = new ArrayList<>(), tpaSerial = new ArrayList<>();
//         Scanner scan; Book book = null; String title, author, price, isbn, genre, year;

//         for(String value: validSyntaxFiles.values()) {
//             try {
//                 scan = new Scanner(new FileInputStream(value));

//                 while(scan.hasNextLine()) {
//                     String line = scan.nextLine().trim();

//                     if(line.startsWith("\"")) {  //line does not start with quotation marks, so just split at each comma(,)
//                         int firstQuote = line.indexOf("\"");
//                         int secondQuote = line.indexOf("\"", firstQuote + 1);

//                         title = line.substring(firstQuote + 1, secondQuote);
//                         String[] withoutTitle = line.substring(secondQuote + 2).split(",");
//                         author = withoutTitle[0]; price = withoutTitle[1]; isbn = withoutTitle[2]; genre = withoutTitle[3]; year = withoutTitle[4];
//                     } else {
//                         String[] withTitle = line.split(",");
//                         title = withTitle[0]; author = withTitle[1]; price = withTitle[2]; isbn = withTitle[3]; genre = withTitle[4]; year = withTitle[5];
//                     }

//                     try {
//                         book = new Book(title, author, Double.parseDouble(price), isbn, genre, Integer.parseInt(year));
//                     } catch (BadPrice e) {
//                         badPrice++;
//                         openPrintWriter(errorFiles.get("SEM"), semError(value.substring(12), String.valueOf(e), line));
//                         continue;
//                     } catch (BadIsbn10 e) {
//                         badIsbn10++;
//                         openPrintWriter(errorFiles.get("SEM"), semError(value.substring(12), String.valueOf(e), line));
//                         continue;
//                     } catch (BadIsbn13 e) {
//                         badIsbn13++;
//                         openPrintWriter(errorFiles.get("SEM"), semError(value.substring(12), String.valueOf(e), line));
//                         continue;
//                     } catch (BadIsbnLength e) {
//                         badIsbnLength++;
//                         openPrintWriter(errorFiles.get("SEM"), semError(value.substring(12), String.valueOf(e), line));
//                         continue;
//                     } catch (UnknownGenre e) {
//                         // already handled
//                     } catch (BadYear e) {
//                         badYear++;
//                         openPrintWriter(errorFiles.get("SEM"), semError(value.substring(12), String.valueOf(e), line));
//                         continue;
//                     }

//                     switch(value) {  //add all syntax-free and semantic-free Book objects in their respective genre list
//                         case "mytextfiles/Cartoons_Comics_Books.csv.txt" -> ccbSerial.add(book);
//                         case "mytextfiles/Hobbies_Collectibles_Books.csv.txt" -> hcbSerial.add(book);
//                         case "mytextfiles/Movies_TV.csv.txt" -> mtvSerial.add(book);
//                         case "mytextfiles/Music_Radio_Books.csv.txt" -> mrbSerial.add(book);
//                         case "mytextfiles/Nostalgia_Eclectic_Books.csv.txt" -> nebSerial.add(book);
//                         case "mytextfiles/Old_Time_Radio.csv.txt" -> otrSerial.add(book);
//                         case "mytextfiles/Sports_Sports_Memorabilia.csv.txt" -> ssmSerial.add(book);
//                         case "mytextfiles/Trains_Planes_Automobiles.csv.txt" -> tpaSerial.add(book);
//                     }
//                 }
//             } catch(FileNotFoundException e) {
//                 System.out.println("File not found for reading.");
//             }
//         }

//         for(String keys: validSemanticSerFiles.keySet()) {  //serialize all lists
//             switch(keys) {
//                 case "CCB" -> serializeAll(validSemanticSerFiles.get(keys), ccbSerial);
//                 case "HCB" -> serializeAll(validSemanticSerFiles.get(keys), hcbSerial);
//                 case "MTV" -> serializeAll(validSemanticSerFiles.get(keys), mtvSerial);
//                 case "MRB" -> serializeAll(validSemanticSerFiles.get(keys), mrbSerial);
//                 case "NEB" -> serializeAll(validSemanticSerFiles.get(keys), nebSerial);
//                 case "OTR" -> serializeAll(validSemanticSerFiles.get(keys), otrSerial);
//                 case "SSM" -> serializeAll(validSemanticSerFiles.get(keys), ssmSerial);
//                 case "TPA" -> serializeAll(validSemanticSerFiles.get(keys), tpaSerial);
//             }
//         }
//     }













//     /**
//      * This function reads the binary files, deserializes each file into a corresponding genre list, and then provides an interactive program to allow the user to navigate the arrays.
//      */
//     private static void do_part3() {
//         List<Book> ccbSerial = new ArrayList<>(), hcbSerial = new ArrayList<>(),  mtvSerial = new ArrayList<>(), mrbSerial = new ArrayList<>(),
//                 nebSerial = new ArrayList<>(), otrSerial = new ArrayList<>(), ssmSerial = new ArrayList<>(), tpaSerial = new ArrayList<>();
//         Scanner scan = new Scanner(System.in); String selectedFile = "";

//         for(String keys: validSemanticSerFiles.keySet()) {  //deserialize all lists
//             switch(keys) {
//                 case "CCB" -> ccbSerial = deserializeAll(validSemanticSerFiles.get(keys));
//                 case "HCB" -> hcbSerial = deserializeAll(validSemanticSerFiles.get(keys));
//                 case "MTV" -> mtvSerial = deserializeAll(validSemanticSerFiles.get(keys));
//                 case "MRB" -> mrbSerial = deserializeAll(validSemanticSerFiles.get(keys));
//                 case "NEB" -> nebSerial = deserializeAll(validSemanticSerFiles.get(keys));
//                 case "OTR" -> otrSerial = deserializeAll(validSemanticSerFiles.get(keys));
//                 case "SSM" -> ssmSerial = deserializeAll(validSemanticSerFiles.get(keys));
//                 case "TPA" -> tpaSerial = deserializeAll(validSemanticSerFiles.get(keys));
//             }
//         }
//         booksCreated = ccbSerial.size() + hcbSerial.size() + mtvSerial.size() + mrbSerial.size() + nebSerial.size()
//                 + otrSerial.size() + ssmSerial.size() + tpaSerial.size();

//         Map<String, List<Book>> allLists = new HashMap<>();
//         allLists.put("Cartoons_Comics_Books.csv.ser", ccbSerial);
//         allLists.put("Hobbies_Collectibles_Books.csv.ser", hcbSerial);
//         allLists.put("Movies_TV.csv.ser", mtvSerial);
//         allLists.put("Music_Radio_Books.csv.ser", mrbSerial);
//         allLists.put("Nostalgia_Eclectic_Books.csv.ser", nebSerial);
//         allLists.put("Old_Time_Radio.csv.ser", otrSerial);
//         allLists.put("Sports_Sports_Memorabilia.csv.ser", ssmSerial);
//         allLists.put("Trains_Planes_Automobiles.csv.ser", tpaSerial);

//         // prints the welcome message
//         System.out.println("""
//                 \n~~~~~ WELCOME, MR. BOOKER! ~~~~~
//                 I will be helping you navigate through your book titles, categorized by genre, as well as providing a facility for identifying and removing invalid book records :)""");

//         while(true) {
//             System.out.println("\n----------------------------- \n          Main Menu          \n-----------------------------");
//             System.out.printf(" v  View the selected file: %s", selectedFile);
//             System.out.println("\n s  Select a file to view");
//             System.out.println(" x  Exit");
//             System.out.println("-----------------------------");

//             //prompting user to enter a choice from the menu above
//             System.out.print("Enter Your Choice: ");
//             String code = scan.next();

//             //--------------------------------------------------------------------------------------------------
//             if(code.equals("v")) {
//                 if(selectedFile.isEmpty()) System.out.println("No file selected.");
//                 else {
//                     System.out.printf("viewing: %s", selectedFile);
//                     while(true) {
//                         try {
//                             System.out.print("\n\nEnter an integer number: ");
//                             int n = scan.nextInt();

//                             if(n == 0) { System.out.println("Viewing ended."); break; }
//                             else {
//                                 for(String key: allLists.keySet()) {
//                                     if(key.equals(selectedFile.substring(0, selectedFile.indexOf(" ")))) {
//                                         validateCommands(n, allLists.get(key));
//                                     }
//                                 }
//                             }
//                         } catch(InputMismatchException e) {
//                             System.out.println("Enter an integer.");
//                         }
//                     }
//                 }
//             }

//             //--------------------------------------------------------------------------------------------------
//             else if(code.equals("s")) {
//                 System.out.println("\n------------------------------ \n        File Sub-Menu          \n------------------------------");
//                 System.out.printf(" 1  Cartoons_Comics_Books.csv.ser           (%d records)", ccbSerial.size());
//                 System.out.printf("\n 2  Hobbies_Collectibles_Books.csv.ser      (%d records)", hcbSerial.size());
//                 System.out.printf("\n 3  Movies_TV.csv.ser                       (%d records)", mtvSerial.size());
//                 System.out.printf("\n 4  Music_Radio_Books.csv.ser               (%d records)", mrbSerial.size());
//                 System.out.printf("\n 5  Nostalgia_Eclectic_Books.csv.ser        (%d records)", nebSerial.size());
//                 System.out.printf("\n 6  Old_Time_Radio.csv.ser                  (%d records)", otrSerial.size());
//                 System.out.printf("\n 7  Sports_Sports_Memorabilia.csv.ser       (%d records)", ssmSerial.size());
//                 System.out.printf("\n 8  Trains_Planes_Automobiles.csv.ser       (%d records)", tpaSerial.size());
//                 System.out.println("\n 9  Exit");
//                 System.out.println("------------------------------");

//                 //prompting user to enter a choice from the menu above
//                 System.out.print("Enter Your Choice: ");
//                 int choice = scan.nextInt();

//                 if(choice == 1) { selectedFile = String.format("Cartoons_Comics_Books.csv.ser (%d records)", ccbSerial.size()); }
//                 else if(choice == 2) { selectedFile = String.format("Hobbies_Collectibles_Books.csv.ser (%d records)", hcbSerial.size()); }
//                 else if(choice == 3) { selectedFile = String.format("Movies_TV.csv.ser (%d records)", mtvSerial.size()); }
//                 else if(choice == 4) { selectedFile = String.format("Music_Radio_Books.csv.ser (%d records)", mrbSerial.size()); }
//                 else if(choice == 5) { selectedFile = String.format("Nostalgia_Eclectic_Books.csv.ser (%d records)", nebSerial.size()); }
//                 else if(choice == 6) { selectedFile = String.format("Old_Time_Radio.csv.ser (%d records)", otrSerial.size()); }
//                 else if(choice == 7) { selectedFile = String.format("Sports_Sports_Memorabilia.csv.ser (%d records)", ssmSerial.size()); }
//                 else if(choice == 8) { selectedFile = String.format("Trains_Planes_Automobiles.csv.ser (%d records)", tpaSerial.size()); }
//                 else if(choice == 9) { System.out.println("File Sub-Menu Exited"); break; }
//                 else { System.out.println("Sorry that is an invalid choice. Try again.\n"); }
//             }

//             //--------------------------------------------------------------------------------------------------
//             else if(code.equals("x")) { System.out.println("Interactive Main Menu terminated.\n"); break; }

//             //--------------------------------------------------------------------------------------------------
//             else { System.out.println("Sorry that is an invalid choice. Try again."); }
//         }
//     }
