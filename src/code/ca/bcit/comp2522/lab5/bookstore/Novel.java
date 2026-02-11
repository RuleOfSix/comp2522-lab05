package ca.bcit.comp2522.lab5.bookstore;

/**
 * Represents a Novel in the bookstore.
 *
 * @author June Pyle
 * @author Damon Cao
 *
 * @version 0.1
 */
public final class Novel implements Comparable<Novel>
{
    private static final int MIN_YEAR_PUBLISHED = -2100;
    private static final int MAX_YEAR_PUBLISHED = 2026;

    private final String title;
    private final String authorName;
    private final int yearPublished;

    /**
     * Constructs a new Novel with the given parameters, if valid.
     *
     * @param title The title of the Novel
     * @param authorName The name of the author of the Novel in one String
     * @param yearPublished The year of publication of the Novel as an integer
     */
    public Novel(final String title,
                 final String authorName,
                 final int yearPublished)
    {
        validateTitle(title);
        validateAuthorName(authorName);
        validateYearPublished(yearPublished);

        this.title = title;
        this.authorName = authorName;
        this.yearPublished = yearPublished;
    }

    /**
     * Accessor for this Novel's title.
     *
     * @return The title of this Novel.
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * Accessor for the name of this Novel's author.
     *
     * @return the author's name in one String
     */
    public String getAuthorName()
    {
        return this.authorName;
    }

    /**
     * Accessor for the year of publication of this Novel.
     *
     * @return the year this Novel was published as an integer.
     */
    public int getYearPublished()
    {
        return this.yearPublished;
    }

    /**
     * Overrides Comparable's compareTo method, compares Novels based on title and calls String's
     * compareToIgnoreCase to do it.
     *
     * @param otherNovel the Novel to be compared.
     *
     * @return int where
     * - Positive integer means this is greater than otherNovel
     * - Negative integer means this is less than otherNovel
     * - 0 means this and otherNovel are equal
     */
    @Override
    public int compareTo(final Novel otherNovel)
    {
        final String otherNovelTitle;

        otherNovelTitle = otherNovel.getTitle();

        return this.title.compareToIgnoreCase(otherNovelTitle);
    }

    /*
     * Validates a novel title. A title is valid if it is not null or blank.
     *
     * @param title the title to validate
     */
    private static void validateTitle(final String title)
    {
        if (title == null ||
            title.isBlank())
        {
            throw new IllegalArgumentException("Invalid title");
        }
    }

    /*
     * Validates a novel's year of publication. A novel must have been published between MIN_YEAR_PUBLISHED
     * and MAX_YEAR_PUBLISHED.
     *
     * @param yearPublished the year of publication to validate
     */
    private static void validateYearPublished(final int yearPublished)
    {
        if (yearPublished < MIN_YEAR_PUBLISHED ||
            yearPublished > MAX_YEAR_PUBLISHED)
        {
            throw new IllegalArgumentException("Invalid novel publiication date.");
        }
    }

    /*
     * Validates a novel author's name. An author's name is valid if it is not null or blank.
     *
     * @param authorName the author name to validate.
     */
    private static void validateAuthorName(final String authorName)
    {
        if (authorName == null ||
            authorName.isBlank())
        {
            throw new IllegalArgumentException("Invalid author name.");
        }
    }
}
