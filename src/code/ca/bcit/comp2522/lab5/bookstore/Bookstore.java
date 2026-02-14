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
    private static final int MIN_BOOKS_IN_RANGE = 0;
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

        populateInventory();
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
     * Prints all titles in alphabetical order, uses Collections.sort() to sort a copy of inventory alphabetically.
     * The original inventory instance variable is not changed.
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
     *
     * @return The percentage of the bookstore's inventory that was published within the given range of years.
     */
    public double whichPercentWrittenBetween(final int firstYear,
                                             final int lastYear)
    {
        if (lastYear < firstYear)
        {
            return NONE_PERCENTAGE;
        }

        final int totalBooks;
        int booksInRange;

        totalBooks = this.inventory.size();
        booksInRange = MIN_BOOKS_IN_RANGE;

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

            if (novelPublishingYear < oldestNovelPublishingYear)
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

    /**
     * Populates inventory.
     */
    private void populateInventory()
    {
        Collections.addAll(inventory,
            new Novel("The Adventures of Augie March", "Saul Bellow", 1953),
            new Novel("All the King’s Men", "Robert Penn Warren", 1946),
            new Novel("American Pastoral", "Philip Roth", 1997),
            new Novel("An American Tragedy", "Theodore Dreiser", 1925),
            new Novel("Animal Farm", "George Orwell", 1946),
            new Novel("Appointment in Samarra", "John O'Hara", 1934),
            new Novel("Are You There God? It's Me, Margaret.", "Judy Blume", 1970),
            new Novel("The Assistant", "Bernard Malamud", 1957),
            new Novel("At Swim-Two-Birds", "Flann O'Brien", 1938),
            new Novel("Atonement", "Ian McEwan", 2002),
            new Novel("Beloved", "Toni Morrison", 1987),
            new Novel("The Berlin Stories", "Christopher Isherwood", 1946),
            new Novel("The Big Sleep", "Raymond Chandler", 1939),
            new Novel("The Blind Assassin", "Margaret Atwood", 2000),
            new Novel("Blood Meridian", "Cormac McCarthy", 1986),
            new Novel("Brideshead Revisited", "Evelyn Waugh", 1946),
            new Novel("The Bridge of San Luis Rey", "Thornton Wilder", 1927),
            new Novel("Call It Sleep", "Henry Roth", 1935),
            new Novel("Catch-22", "Joseph Heller", 1961),
            new Novel("The Catcher in the Rye", "J.D. Salinger", 1951),
            new Novel("A Clockwork Orange", "Anthony Burgess", 1963),
            new Novel("The Confessions of Nat Turner", "William Styron", 1967),
            new Novel("The Corrections", "Jonathan Franzen", 2001),
            new Novel("The Crying of Lot 49", "Thomas Pynchon", 1966),
            new Novel("A Dance to the Music of Time", "Anthony Powell", 1951),
            new Novel("The Day of the Locust", "Nathanael West", 1939),
            new Novel("Death Comes for the Archbishop", "Willa Cather", 1927),
            new Novel("A Death in the Family", "James Agee", 1958),
            new Novel("The Death of the Heart", "Elizabeth Bowen", 1958),
            new Novel("Deliverance", "James Dickey", 1970),
            new Novel("Dog Soldiers", "Robert Stone", 1974),
            new Novel("Falconer", "John Cheever", 1977),
            new Novel("The French Lieutenant's Woman", "John Fowles", 1969),
            new Novel("The Golden Notebook", "Doris Lessing", 1962),
            new Novel("Go Tell It on the Mountain", "James Baldwin", 1953),
            new Novel("Gone with the Wind", "Margaret Mitchell", 1936),
            new Novel("The Grapes of Wrath", "John Steinbeck", 1939),
            new Novel("Gravity's Rainbow", "Thomas Pynchon", 1973),
            new Novel("The Great Gatsby", "F. Scott Fitzgerald", 1925),
            new Novel("A Handful of Dust", "Evelyn Waugh", 1934),
            new Novel("The Heart Is a Lonely Hunter", "Carson McCullers", 1940),
            new Novel("The Heart of the Matter", "Graham Greene", 1948),
            new Novel("Herzog", "Saul Bellow", 1964),
            new Novel("Housekeeping", "Marilynne Robinson", 1981),
            new Novel("A House for Mr. Biswas", "V.S. Naipaul", 1962),
            new Novel("I, Claudius", "Robert Graves", 1934),
            new Novel("Infinite Jest", "David Foster Wallace", 1996),
            new Novel("Invisible Man", "Ralph Ellison", 1952),
            new Novel("Light in August", "William Faulkner", 1932),
            new Novel("The Lion, The Witch and the Wardrobe", "C.S. Lewis", 1950),
            new Novel("Lolita", "Vladimir Nabokov", 1955),
            new Novel("Lord of the Flies", "William Golding", 1954),
            new Novel("The Lord of the Rings", "J.R.R. Tolkien", 1954),
            new Novel("Loving", "Henry Green", 1945),
            new Novel("Lucky Jim", "Kingsley Amis", 1954),
            new Novel("The Man Who Loved Children", "Christina Stead", 1940),
            new Novel("Midnight's Children", "Salman Rushdie", 1981),
            new Novel("Money", "Martin Amis", 1984),
            new Novel("The Moviegoer", "Walker Percy", 1961),
            new Novel("Mrs. Dalloway", "Virginia Woolf", 1925),
            new Novel("Naked Lunch", "William Burroughs", 1959),
            new Novel("Native Son", "Richard Wright", 1940),
            new Novel("Neuromancer", "William Gibson", 1984),
            new Novel("Never Let Me Go", "Kazuo Ishiguro", 2005),
            new Novel("1984", "George Orwell", 1948),
            new Novel("On the Road", "Jack Kerouac", 1957),
            new Novel("One Flew Over the Cuckoo's Nest", "Ken Kesey", 1962),
            new Novel("The Painted Bird", "Jerzy Kosinski", 1965),
            new Novel("Pale Fire", "Vladimir Nabokov", 1962),
            new Novel("A Passage to India", "E.M. Forster", 1924),
            new Novel("Play It as It Lays", "Joan Didion", 1970),
            new Novel("Portnoy's Complaint", "Philip Roth", 1969),
            new Novel("Possession", "A.S. Byatt", 1990),
            new Novel("The Power and the Glory", "Graham Greene", 1939),
            new Novel("The Prime of Miss Jean Brodie", "Muriel Spark", 1961),
            new Novel("Rabbit, Run", "John Updike", 1960),
            new Novel("Ragtime", "E.L. Doctorow", 1975),
            new Novel("The Recognitions", "William Gaddis", 1955),
            new Novel("Red Harvest", "Dashiell Hammett", 1929),
            new Novel("Revolutionary Road", "Richard Yates", 1961),
            new Novel("The Sheltering Sky", "Paul Bowles", 1949),
            new Novel("Slaughterhouse-Five", "Kurt Vonnegut", 1969),
            new Novel("Snow Crash", "Neal Stephenson", 1992),
            new Novel("The Sot-Weed Factor", "John Barth", 1960),
            new Novel("The Sound and the Fury", "William Faulkner", 1929),
            new Novel("The Sportswriter", "Richard Ford", 1986),
            new Novel("The Spy Who Came in from the Cold", "John le Carré", 1964),
            new Novel("The Sun Also Rises", "Ernest Hemingway", 1926),
            new Novel("Their Eyes Were Watching God", "Zora Neale Hurston", 1937),
            new Novel("Things Fall Apart", "Chinua Achebe", 1959),
            new Novel("To Kill a Mockingbird", "Harper Lee", 1960),
            new Novel("To the Lighthouse", "Virginia Woolf", 1929),
            new Novel("Tropic of Cancer", "Henry Miller", 1934),
            new Novel("Ubik", "Philip K. Dick", 1969),
            new Novel("Under the Net", "Iris Murdoch", 1954),
            new Novel("Under the Volcano", "Malcolm Lowry", 1947),
            new Novel("Watchmen", "Alan Moore and Dave Gibbons", 1986),
            new Novel("White Noise", "Don DeLillo", 1985),
            new Novel("White Teeth", "Zadie Smith", 2000),
            new Novel("Wide Sargasso Sea", "Jean Rhys", 1966)
        );
    }

    public static void main(final String[] args)
    {
        final Bookstore bookstore;
        final Novel oldest;
        final List<Novel> fifteenCharTitles;
        bookstore = new Bookstore("Classic Novels Collection");
        System.out.println("All Titles in UPPERCASE:");
        bookstore.printAllTitles();
        System.out.println("\nBook Titles Containing 'the':");
        bookstore.printBookTitle("the");
        System.out.println("\nAll Titles in Alphabetical Order:");
        bookstore.printTitlesInAlphaOrder();
        System.out.println("\nBooks from the 2000s:");
        bookstore.printGroupByDecade(2000);
        System.out.println("\nLongest Book Title:");
        System.out.println(bookstore.getLongest());
        System.out.println("\nIs there a book written in 1950?");
        System.out.println(bookstore.isThereABookWrittenIn(1950));
        System.out.println("\nHow many books contain 'Heart'?");
        System.out.println(bookstore.howManyBooksContain("Heart"));
        System.out.println("\nPercentage of books written between 1940 and 1950:");
        System.out.println(bookstore.whichPercentWrittenBetween(1940, 1950) + "%");
        System.out.println("\nOldest book:");
        oldest = bookstore.getOldestBook();
        System.out.println(oldest.getTitle() + " by " + oldest.getAuthorName() + ", " +
                oldest.getYearPublished());
        System.out.println("\nBooks with titles 15 characters long:");
        fifteenCharTitles = bookstore.getBooksThisLength(15);
        fifteenCharTitles.forEach(novel -> System.out.println(novel.getTitle()));
    }
}
