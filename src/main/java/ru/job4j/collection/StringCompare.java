package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int len = Math.min(o1.length(), o2.length());
        int res = 0;
        for (int i = 0; i < len; i++) {
            res = Character.compare(o1.charAt(i), o2.charAt(i));
            if (res != 0) {
                break;
            }
        }
        return res == 0 ? Integer.compare(o1.length(), o2.length()) : res;
    }
}