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
    private static final int YEARS_PER_DECADE = 10;
    private static final int YEARS_PER_CENTURY = 100;
    private static final double NONE_PERCENTAGE = 0.0;
    private static final double PERCENT_CONVERSION_FACTOR = 100.0;

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
        for (final Novel novel : this.inventory)
        {
            final String novelTitle;
            final String novelTitleLower;
            final String titleLower;

            novelTitle = novel.getTitle();
            novelTitleLower = novelTitle.toLowerCase();
            titleLower = title.toLowerCase();

            if (novelTitleLower.contains(titleLower))
            {
                System.out.println(novelTitle);
            }
        }
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

    /**
     * Prints the titles of all books in the bookstore that were printed in the given decade. A decade is
     * considered to extend from the zeroth to ninth year; for example, books printed from 2000 to 2009, inclusive,
     * are considered to have been printed in the 2000s.
     *
     * @param decade The decade of books to print, as an integer year. This value will be rounded down to the nearest
     *               decade, so the function will consider the target decade to be the decade containing the input year.
     */
    public void printGroupByDecade(final int decade)
    {
        final int decadeStart;
        final int decadeEnd;

        decadeStart = (decade / YEARS_PER_CENTURY) * YEARS_PER_CENTURY;
        decadeEnd = decadeStart + YEARS_PER_DECADE;

        for (final Novel novel : inventory)
        {
            final int yearPublished;
            final String title;

            yearPublished = novel.getYearPublished();

            if (decadeStart <= yearPublished &&
                decadeEnd > yearPublished)
            {
                title = novel.getTitle();

                System.out.println(title);
            }
        }


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

    /**
     * Determines whether the bookstore has a book written in the given year.
     *
     * @param year the year to find a book written in
     *
     * @return true if the bookstore has a book written that year, false otherwise.
     */
    public boolean isThereABookWrittenIn(final int year)
    {
        for (final Novel novel : this.inventory)
        {
            final int yearPublished;

            yearPublished = novel.getYearPublished();

            if (year == yearPublished)
            {
                return true;
            }
        }

        return false;
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

    /**
     * Determines what percentage of the bookstore's inventory was written between firstYear and lastYear,
     * inclusive.
     *
     * @param firstYear The beginning year of the range.
     * @param lastYear The final year of the range.
     * @return The percentage (0 to 100) of the bookstore's inventory that was published within the given range of
     *         years.
     */
    public double whichPercentWrittenBetween(final int firstYear, final int lastYear)
    {
        if (lastYear < firstYear)
        {
            return NONE_PERCENTAGE;
        }

        final int totalBooks;
        int booksInRange;
        totalBooks = this.inventory.size();
        booksInRange = 0;

        for (final Novel novel : this.inventory)
        {
            final int yearPublished;
            yearPublished = novel.getYearPublished();
            if (yearPublished >= firstYear &&
                yearPublished <= lastYear)
            {
                booksInRange++;
            }
        }

        final double ratio;
        ratio = (double) booksInRange / totalBooks;

        return ratio * PERCENT_CONVERSION_FACTOR;
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

    /**
     * Returns all books in the bookstore whose titles are of the given length.
     *
     * @param titleLength the length of titles to get books of
     *
     * @return a List of the novels the bookstore has that have titles of the given length
     */
    public List<Novel> getBooksThisLength(final int titleLength)
    {
        final List<Novel> novelsOfLength;

        novelsOfLength = new ArrayList<>();

        for (final Novel novel : this.inventory)
        {
            final String title;
            final int currentTitleLength;

            title = novel.getTitle();
            currentTitleLength = title.length();

            if (currentTitleLength == titleLength)
            {
                novelsOfLength.add(novel);
            }
        }

        return novelsOfLength;
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
