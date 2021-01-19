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
 * Represents bibliographical information about an article published in a journal
 */
public class Article extends Reference {
    private int endingPage;
    private int startingPage;
    private String journal;

    public Article() {
        super();
    }

    /**
     * Prompts the user for intial attribute values of the article object
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
        out.println("Enter the title of the journal");
        journal = in.nextLine();
        out.println("Enter the first page number of the article");
        startingPage = in.nextInt();
        in.nextLine(); // catch newline
        out.println("Enter the last page number of the article");
        endingPage = in.nextInt();
        in.nextLine(); // catch newline
    }

    /**
     * Prompts the user to update the attributes of the article object
     * @param out Output stream to prompt the user for input
     * @param in Input stream to read user input
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
        out.println("Enter the updated title of the journal");
        journal = in.nextLine();
        out.println("Enter the updated first page number of the article");
        startingPage = in.nextInt();
        in.nextLine(); // catch newline
        out.println("Enter the updated last page number of the article");
        endingPage = in.nextInt();
        in.nextLine(); // catch newline
    }

    /**
     * Return a string of information in BibTeX format
     * @return the string of information in BibTeX format
     */
    @Override
    public String toString() {
        return String.format(
                "@ARTICLE { %s,\n" +
                        "author = \"%s\",\n" +
                        "title = \"%s\",\n" +
                        "journal = \"%s\",\n" +
                        "pages = \"%s-%s\",\n" +
                        "year = \"%s\"\n" +
                        "}", myUniqueID, author, title, journal, startingPage, endingPage, publicationYear);
    }

    public int getEndingPage() {
        return endingPage;
    }

    public int getStartingPage() {
        return startingPage;
    }

    public String getJournal() {
        return journal;
    }

    /**
     * Sets the ending page of the Article. IF the desired ending page is less than the starting page, no change is made.
     * @param endingPage Page number of the last page of the article
     * @return returns true if the page was changed
     */
    private boolean setEndingPage(int endingPage) {
        boolean changed = this.endingPage != endingPage || endingPage < 0 || endingPage >= this.startingPage;

        this.endingPage = endingPage;
        return changed;
    }

    /**
     * Sets first page of the article. If the desired starting page is not positive or is greater than the current ending page, no change is made.
     * @param startingPage Page number of the first page of the article.
     * @return returns true of the page was changed
     */
    private boolean setStartingPage(int startingPage) {
        boolean changed = this.endingPage != endingPage || startingPage < 0 || this.endingPage >= startingPage;

        this.startingPage = startingPage;
        return changed;
    }
}
