package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String str1 = o1.split("/")[0];
        String str2 = o2.split("/")[0];
        int result = str2.compareTo(str1);
        return result != 0 ? result : o1.compareTo(o2);
    }
}