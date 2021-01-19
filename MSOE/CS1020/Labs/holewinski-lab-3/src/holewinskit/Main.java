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

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Main {
    private static  final int UNSELECTED = -1;
    private static final int EXIT = 0;
    private static final int NEW_BOOK = 1;
    private static final int NEW_ARTICLE = 2;
    private static final int UPDATE = 3;
    private static final int PRINTOUT = 4;
    private static final int REMOVE = 5;

    static void printMenu() {
        out.println("Enter " + EXIT + " to exit the program.");
        out.println("Enter " + NEW_BOOK + " to enter a new book into the reference set.");
        out.println("Enter " + NEW_ARTICLE + " to enter a new article into the reference set.");
        out.println("Enter " + UPDATE + " to update a reference.");
        out.println("Enter " + PRINTOUT + " to printout the entries in Bibtex format.");
        out.println("Enter " + REMOVE + " to remove a reference.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        ReferenceHolder holder = new ReferenceHolder();
        int selection = UNSELECTED;

        while (selection != EXIT) {
            printMenu();
            selection = scanner.nextInt();
            scanner.nextLine(); // catch newline

            switch (selection) {
                case EXIT:
                    out.println("Goodbye");
                    break;
                case NEW_BOOK:
                    Book book = new Book();
                    book.promptToInitialize(out, scanner);
                    holder.addReference(book);
                    break;
                case NEW_ARTICLE:
                    Article article = new Article();
                    article.promptToInitialize(out, scanner);
                    holder.addReference(article);
                    break;
                case UPDATE:
                    out.println("Enter the ID of the reference you want to update");
                    holder.updateReference(scanner.nextLine(), out, scanner);
                    break;
                case PRINTOUT:
                    out.println(holder.toString());
                    break;
                case REMOVE:
                    out.println("Enter the ID of the reference you want to remove");
                    holder.removeReference(scanner.nextLine());
                    break;
            }
        }
    }
}
