
/**
 * this is the main class that runs the library catalog.
 */
public class Library {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        Helper helper = new Helper();

        catalog.validateSyntaxAndPartition();
        catalog.validateSemanticsAndSerialize();
        catalog.deserializeAndNavigateCatalog();

        //total syntax and semantic errors
        int syntaxErrors = helper.tooFewFields + helper.tooManyFields + helper.unknownGenre + helper.missingFields;
        int semanticErrors = helper.badIsbn10 + helper.badIsbn13 + helper.badIsbnLength + helper.badPrice + helper.badYear;

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
                """, helper.booksCreated, syntaxErrors, semanticErrors, helper.tooFewFields, helper.tooManyFields, helper.missingFields, 
                helper.unknownGenre, helper.badPrice, helper.badIsbn10, helper.badIsbn13, helper.badIsbnLength, helper.badYear);

        //displays the closing message
        System.out.print("\nGoodbye Mr. Booker! Hope to see you sometime later!!!");
        //delete the files
        helper.deleteFiles();
    }
}
