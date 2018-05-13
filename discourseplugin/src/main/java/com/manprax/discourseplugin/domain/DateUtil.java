package com.manprax.discourseplugin.domain;

import com.google.gson.internal.bind.util.ISO8601Utils;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

/**
 * Created by mukesh on 20/4/18.
 */

public class DateUtil {
    /*
     * Converting Date in string format to Date object and converting the Current
     * Stamp
     */
    public static Date convertToDate(String date) {
        if(date==null){
            return null;
        }

        java.util.Date parsedate = null;
        final ParsePosition parsePosition = new ParsePosition(0);
        try {
            parsedate = ISO8601Utils.parse(date, parsePosition);
            return parsedate;

        } catch (ParseException e) {
           e.printStackTrace();
        }
        return parsedate;
    }
}
