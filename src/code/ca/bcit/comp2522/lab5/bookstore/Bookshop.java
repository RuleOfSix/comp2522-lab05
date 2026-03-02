package ca.bcit.comp2522.lab5.bookstore;

import java.util.*;

/**
 * Represents a bookshop with an inventory of Novels.
 *
 * @author Damon Cao
 * @author June Pyle
 */
public class Bookshop
{
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

        final Iterator<String> titleIterator = titleSet.iterator();
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
