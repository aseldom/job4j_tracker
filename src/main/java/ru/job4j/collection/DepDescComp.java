package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String str1 = o1.split("/")[0];
        String str2 = o2.split("/")[0];
        if (str1.compareTo(str2) == 0) {
            return o1.compareTo(o2);
        }
        return o2.compareTo(o1);
    }
}