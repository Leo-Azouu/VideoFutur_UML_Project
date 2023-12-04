package fr.efrei.Util;

import fr.efrei.domain.Category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Helper {
    public static Boolean isStringValid(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isNumericEmpty(int id) {
        if (id == 0) {
            return true;
        } else {
            return false;
        }
    }
    public static Boolean isDoubleEmpty(double x) {
        if (x == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isDateValid(Date date) {
        if (date == null) {
            return false;
        } else {
            return true;
        }
    }
    public static <T> boolean isListEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

public static Date parseStringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

}
