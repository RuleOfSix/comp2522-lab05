package ca.bcit.comp2522.lab5.bookstore;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

/**
 * Represents a bookshop with an inventory of Novels.
 *
 * @author Damon Cao
 * @author June Pyle
 */
public class Bookshop
{
    /**
     * Creates a Map of Novels where their titles are the key, filled with the default inventory of a Bookstore.
     * Prints out all titles, then removes all novels whose titles contain the word 'the', and then prints out all
     * novel information in alphabetical order.
     */
    public Bookshop()
    {
        final Map<String, Novel> inventoryMap;
        final Set<String> titleSet;
        final Bookstore bookstore;

        bookstore = new Bookstore("Inventory source");
        inventoryMap = new HashMap<>();
        for (final Novel novel : bookstore.getInventory())
        {
            inventoryMap.put(novel.getTitle(), novel);
        }
        titleSet = inventoryMap.keySet();

        final Iterator<String> titleIterator;
        titleIterator = titleSet.iterator();
        while (titleIterator.hasNext())
        {
            final String title;
            final String lowerTitle;

            title = titleIterator.next();
            System.out.println(title);
            lowerTitle = title.toLowerCase();
            if (lowerTitle.contains("the"))
            {
                titleIterator.remove();
            }
        }

        final List<String> titleList;
        titleList = new ArrayList<>(titleSet);
        Collections.sort(titleList);

        for (final String title : titleList)
        {
            final Novel novel;
            novel = inventoryMap.get(title);
            System.out.println(novel);
        }
    }

}
