package ca.bcit.comp2522.lab5.bookstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Represents the entire contents of the bookstore. A Bookstore holds Novels. Contains a main() method which
 * demonstrates the Bookstore's functionality.
 *
 * @author June Pyle
 * @author Damon Cao
 *
 * @version 0.1
 */
public final class Bookstore
{
    private static final int MIN_NUM_BOOKS_CONTAINING_WORD = 0;

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

    /**
     * Prints all titles in alphabetical order using Collections.sort().
     */
    public void printTitlesInAlphaOrder()
    {
        final List<Novel> alphabeticalInventory;
        alphabeticalInventory = new ArrayList<>();

        alphabeticalInventory.addAll(this.inventory);

        Collections.sort(alphabeticalInventory);

        for (final Novel novel : alphabeticalInventory)
        {
            final String title;
            title = novel.getTitle();

            System.out.println(title);
        }

    }

    // June
    public int printGroupByDecade(final int Decade)
    {

    }

    /**
     * Returns the longest title in the inventory.
     *
     * @return the String of the longest book title
     */
    public String getLongest()
    {
        String longestTitle;
        longestTitle = this.inventory.getFirst().getTitle();

        for (final Novel novel : this.inventory)
        {
            final int longestTitleLength;
            final int novelTitleLength;

            longestTitleLength = longestTitle.length();
            novelTitleLength = novel.getTitle().length();

            if (novelTitleLength > longestTitleLength)
            {
                longestTitle = novel.getTitle();
            }
        }

        return longestTitle;
    }

    // June
    public boolean isThereABookWrittenIn(final int year)
    {

    }

    /**
     * Returns the number of book titles containing a provided String word.
     *
     * @param word is the String that book titles contain
     *
     * @return int representing number of books containing word
     */
    public int howManyBooksContain(final String word)
    {
        int numBooksContainingWord;
        numBooksContainingWord = MIN_NUM_BOOKS_CONTAINING_WORD;

        for (final Novel novel : this.inventory)
        {
            final String novelTitle;
            novelTitle = novel.getTitle();

            if (novelTitle.contains(word))
            {
                numBooksContainingWord++;
            }
        }

        return numBooksContainingWord;
    }

    // June
    public double whichPercentWrittenBetween(final int firstYear, final int lastYear)
    {

    }

    /**
     * Returns the oldest book in the inventory based on publishing year.
     *
     * @return Novel with oldest publishing year
     */
    public Novel getOldestBook()
    {
        Novel oldestNovel;
        oldestNovel = this.inventory.getFirst();

        for (final Novel novel : this.inventory)
        {
            final int oldestNovelPublishingYear;
            final int novelPublishingYear;

            oldestNovelPublishingYear = oldestNovel.getYearPublished();
            novelPublishingYear = novel.getYearPublished();

            if (novelPublishingYear > oldestNovelPublishingYear)
            {
                oldestNovel = novel;
            }
        }

        return oldestNovel;
    }

    // June
    public List<Novel> getBooksThisLength(final int titleLength)
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
