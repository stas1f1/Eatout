package com.fiit.eatout.eatout.globalValues;

import com.fiit.eatout.eatout.network.ProductEntry;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static List<ProductEntry> contents = new ArrayList<>();

    public static void addEntry(ProductEntry product)
    {
        contents.add(product);
    }

    public static void removeEntry(int position)
    {
        contents.remove(position);
    }

    public static List<ProductEntry> getContents()
    {
        return contents;
    }

    public static void clear()
    {
        contents.clear();
    }

    public static int getPrice()
    {
        int price = 0;
        for (ProductEntry entry : contents)
            price += Integer.parseInt(entry.price);
        return price;
    }

    public static String toJSON()
    {
        String res = "";
        for (ProductEntry entry : contents) {
            if (!res.isEmpty())
                res += ",";
            res += "\"" + entry.ID + "\"";
        }
        return "[" + res + "]";
    }

    public static Boolean isEmpty()
    {
        return contents.isEmpty();
    }

    public static int getCookingTime()
    {
        int time = 0;
        for (ProductEntry entry : contents)
            time += Integer.parseInt(entry.time);
        return time;
    }
}
