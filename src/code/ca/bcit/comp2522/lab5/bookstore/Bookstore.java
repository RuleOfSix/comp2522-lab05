package ca.bcit.comp2522.lab5.bookstore;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the entire contents of the bookstore. A Bookstore holds Novels. Contains a main() method which
 * demonstrates the Bookstore's functionality.
 *
 * @author June Pyle
 *
 * @version 0.1
 */
public final class Bookstore
{
    private final String storeName;
    private final List<Novel> inventory;

    /**
     * Constructs a new Bookstore with the given name and a predetermined initial inventory of Novels.
     *
     * @param storeName The name of the Bookstore to construct
     */
    public Bookstore(final String storeName)
    {
        validateStoreName(storeName);
        this.storeName = storeName;

        this.inventory = new ArrayList<>();
    }

    /**
     * Prints the title of every novel in the bookstore in all UPPERCASE.
     */
    public void printAllTitles()
    {
        for (final Novel novel : this.inventory)
        {
            final String titleUpperCase;
            titleUpperCase = novel.getTitle().toUpperCase();
            System.out.println(titleUpperCase);
        }
    }

    /**
     * Prints the titles of all books that contain the given String
     *
     * @param title the String to search for in the book titles
     */
    public void printBookTitle(final String title)
    {

    }

    /*
     * Validates the Bookstore's name. A store name is valid if it is not null or blank.
     *
     * @param storeName the store name to validate
     */
    private static void validateStoreName(final String storeName)
    {
        if (storeName == null ||
            storeName.isBlank())
        {
            throw new IllegalArgumentException("Invalid store name.");
        }
    }
}
