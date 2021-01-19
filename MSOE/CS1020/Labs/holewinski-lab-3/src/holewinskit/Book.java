/**
 * Course: CS 1021
 * =======================
 * Name: Tyler Holewinski
 * Date: 12/15/2020
 * =======================
 * holewinskit@msoe.edu
 * gh/erwijet
 */


package holewinskit;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents bibliographical information about a book
 */
public class Book extends Reference {
    private String publisher;

    public Book() {
        super();
    }

    /**
     * Prompts the user for initial attribute values of the article object
     * @param out
     * @param in
     */
    public void promptToInitialize(PrintStream out, Scanner in) {
        out.println("Enter the author of the reference");
        author = in.nextLine();
        out.println("Enter the title of the reference");
        title = in.nextLine();
        out.println("Enter the copyright year of the reference");
        publicationYear = in.nextInt();
        in.nextLine(); // catch newline
        out.println("Enter the publisher of the book");
        publisher = in.nextLine();
    }

    public String getPublisher() {
        return publisher;
    }

    /**
     * Prompts the user to update the attributes of the article object
     *
     * @param out Output stream to prompt the user for input
     * @param in  Input stream to read user input
     */
    @Override
    public void promptForUpdate(PrintStream out, Scanner in) {
        out.println("Enter the updated author of the reference");
        author = in.nextLine();
        out.println("Enter the updated title of the reference");
        title = in.nextLine();
        out.println("Enter the updated copyright year of the reference");
        publicationYear = in.nextInt();
        in.nextLine(); // catch newline
        out.println("Enter the updated publisher of the book");
        publisher = in.nextLine();
    }

    /**
     * Return a string of information in BibTeX format
     * @return the information in BibTeX format
     */
    @Override
    public String toString() {
        return String.format(
                "@BOOK { %s,\n" +
                        "author = \"%s\",\n" +
                        "title = \"%s\",\n" +
                        "publisher = \"%s\",\n" +
                        "year = \"%s\"\n" +
                        "}", myUniqueID, author, title, publisher, publicationYear);
    }
}
