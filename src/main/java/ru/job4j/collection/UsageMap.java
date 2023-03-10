package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("abc.mail.ru", "Vasya Pupkin");
        map.put("qwe.mail.ru", "Kolya Svyatkin");
        map.put("dfr.mail.ru", "Misha Utkin");
        map.put("abc.mail.ru", "Prosto Vera");
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }
}
