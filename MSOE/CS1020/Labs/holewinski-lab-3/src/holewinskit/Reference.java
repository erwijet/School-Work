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
 * Represent bibliographical information about a reference
 */
public abstract class Reference {
    private static int instanceCount = 0;

    protected String author;
    protected final String myUniqueID;
    protected int publicationYear;
    protected String title;

    public Reference() {
        myUniqueID = "REF" + instanceCount++;
    }

    /**
     * Prompts the user for initial attribute values of the object
     * @param out
     * @param in
     */
    public abstract void promptToInitialize(PrintStream out, Scanner in);

    /**
     * Prompts the user to update the attributes of the article object
     * @param out Output stream to prompt the user for input
     * @param in Input stream to read user input
     */
    public void promptForUpdate(PrintStream out, Scanner in) {
    }

    public String getAuthor() {
        return this.author;
    }

    public String getMyUniqueID() {
        return this.myUniqueID;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public String getTitle() {
        return this.title;
    }
}
