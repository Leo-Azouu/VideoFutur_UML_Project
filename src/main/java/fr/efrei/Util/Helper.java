package fr.efrei.Util;

import fr.efrei.domain.Category;

import java.util.Date;

public class Helper {
    public static Boolean isStringValid(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isNumericEmpty(Integer integer) {
        if (integer == null) {
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

    public static Boolean isCategoryValid(Category category) {
        if (category == null) {
            return false;
        } else {
            return true;
        }
    }
}
