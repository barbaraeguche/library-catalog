
/**
 * this is the main class that runs the library catalog.
 */
public class Library {
    public static void main(String[] args) {
        Catalog catalog = new Catalog(); 

        catalog.validateSyntaxAndPartition();
        catalog.validateSemanticsAndSerialize();
        catalog.deserializeAndNavigateCatalog();

        //total syntax and semantic errors
        int syntaxErrors = Helper.tooFewFields + Helper.tooManyFields + Helper.unknownGenre + Helper.missingFields;
        int semanticErrors = Helper.badIsbn10 + Helper.badIsbn13 + Helper.badIsbnLength + Helper.badPrice + Helper.badYear;

        //displays the descriptive catalog outputs
        System.out.printf("""
            --------------------------------
            Here are the details after processing all the files;
            --------------------------------
            Total Books Created: %d.
            Total Syntax Errors: %d.
            Total Semantic Errors: %d.
                ----------------
            Total TooFewFields Errors: %d.
            Total MissingField Errors: %d.
            Total MissingField Errors: %d.
            Total InvalidGenre Errors: %d.
                ----------------
            Total Bad Price Errors: %d.
            Total BadISBN10 Errors: %d.
            Total BadISBN13 Errors: %d.
            Total BadISBNLength Errors: %d.
            Total BadYear Errors: %d.
            """, Helper.booksCreated, syntaxErrors, semanticErrors, Helper.tooFewFields, Helper.tooManyFields, Helper.missingFields, 
            Helper.unknownGenre, Helper.badPrice, Helper.badIsbn10, Helper.badIsbn13, Helper.badIsbnLength, Helper.badYear);

        //displays the closing message
        System.out.print("\nGood bye for now. Hope to see you sometime soon!!!");
        //delete the files
        (new Helper()).deleteFiles();
    }
}
