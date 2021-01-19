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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a collection of BibTeX references. Objects from this class can be used to keep track of all references
 * associated with a paper. Once created, references to books and articles can be added to the object. In addition,
 * the class provides a mechanism for updating and removing references that have already been added to the object.
 */
public class ReferenceHolder {
    private final List<Reference> references;

    /**
     * Constructor for ReferenceHolder object
     */
    public ReferenceHolder() {
        references = new ArrayList<Reference>();
    }

    /**
     * Add a book reference to the bibliography
     * @param book the book to be added
     */
    public void addReference(Book book) {
        references.add(book);
    }

    /**
     * Add an article reference to the bibliography
     * @param article the article reference to be added
     */
    public void addReference(Article article) {
        references.add(article);
    }

    /**
     * Removes the reference with the specified ID
     * @param uniqueID the id for the reference to be updated
     * @return true if and only if the desired reference was removed
     */
    public boolean removeReference(String uniqueID) {
        boolean removed = false;

        for (int i = 0; i < references.size() && !removed; i++) {
            if (references.get(i).getMyUniqueID().equals(uniqueID)) {
                references.remove(i);
                removed = true;
            }
        }

        return removed;
    }

    /**
     * Asks the reference corresponding to the uniqueID to update itself.
     * If no reference with uniqueID is found, no update is performed and the method returns false
     * @param uniqueID the id for the reference to be updated
     * @param out Output stream to prompt the user for input
     * @param in Input stream to read user input
     * @return true if and only if the desired reference was updated
     */
    public boolean updateReference(String uniqueID, PrintStream out, Scanner in) {
        boolean found = false;

        for (int i = 0; i < references.size() && !found; i++) {
            if (references.get(i).getMyUniqueID().equals(uniqueID)) {
                references.get(i).promptForUpdate(out, in);
                found = true;
            }
        }

        return found;
    }

    /**
     * Return a string containing all BibTeX entries in the reference list
     * @return a string containing all BibTeX entries
     */
    @Override
    public String toString() {
        String entries = "";
        for (Reference _ref:
             references) {
            entries += _ref.toString() + "\n";
        }

        return entries;
    }
}
