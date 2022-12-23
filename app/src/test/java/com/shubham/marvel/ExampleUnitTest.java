package com.shubham.marvel;

import static com.shubham.marvel.utils.AppConstants.API_KEY;
import static com.shubham.marvel.utils.AppConstants.GET_COMICS_DATE;
import static com.shubham.marvel.utils.AppConstants.GET_SIMPLE_DATE;

import org.junit.Test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkAPIKeyAvailableOrNot() {
        if (!API_KEY.equals("")) {
            System.out.println("API_KEY " + API_KEY);
        }
        else {
            System.out.println("API_KEY not found");
        }
    }

    @Test
    public void convertDateFormat() {
        String newTime;
        SimpleDateFormat actual = new SimpleDateFormat(GET_COMICS_DATE);
        SimpleDateFormat target = new SimpleDateFormat(GET_SIMPLE_DATE);
        Date date;
        try {
            date = actual.parse("2022-03-03");
            newTime = target.format(date);
            System.out.println("newTime " + newTime);
        } catch (ParseException e) {
            System.out.println("exception caught exception");
            e.printStackTrace();
        }
    }
}