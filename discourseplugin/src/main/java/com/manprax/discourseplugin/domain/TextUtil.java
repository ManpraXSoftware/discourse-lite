package com.manprax.discourseplugin.domain;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by mukesh on 20/4/18.
 */

public class TextUtil {
    public static String format(int value) {
        final NavigableMap<Integer, String> suffixes = new TreeMap<>();
        suffixes.put(1_000, "k");
        suffixes.put(1_000_000, "M");
        suffixes.put(1_000_000_000, "G");
        //Integer.MIN_VALUE == -Integer.MIN_VALUE so we need an adjustment here
        if (value == Integer.MIN_VALUE) return format(Integer.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Integer, String> e = suffixes.floorEntry(value);
        Integer divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
//        String numberString = "";
//        if (Math.abs(number / 1000000) > 1) {
//            numberString = (number / 1000000) + "m";
//        } else if (Math.abs(number / 1000) > 1) {
//            numberString = (number / 1000)+ "k";
//        }else{
//            numberString = String.valueOf(number);
//        }
//        return numberString;
    }
}
