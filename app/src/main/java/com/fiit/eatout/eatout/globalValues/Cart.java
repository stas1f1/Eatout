package com.fiit.eatout.eatout.globalValues;

import com.fiit.eatout.eatout.network.ProductEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public static int getSize()
    {
        return contents.size();
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

    public static String ConvertTime(double secs)
    {
        double mins = secs / 60;
        String suffix;
        int prelastDigit = ((int)Math.floor(mins) % 100)/10;
        int lastDigit = (int)Math.floor(mins) % 10;

        if (prelastDigit == 1 || lastDigit == 0 || lastDigit > 4) suffix = " минут";
        else if (lastDigit == 1) suffix = " минутa";
        else suffix = " минуты";

        return fmt(mins) + suffix;
    }

    public static String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%s",(long)d);
        else
            return String.format(Locale.getDefault(),"%.1f",d);
    }

    public static String getFormattedApproxCookingTime()
    {
        int time = 0;
        String prefix = "";
        for (ProductEntry entry : contents)
            time += Integer.parseInt(entry.time);
        if (Cart.getSize() > 1) {
            time *= 0.75;
            prefix = "~";
        }
        return prefix + ConvertTime(time);
    }
}
