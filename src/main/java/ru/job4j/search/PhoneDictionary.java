package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> containsName = s -> s.getName().contains(key);
        Predicate<Person> containsSurname = s -> s.getSurname().contains(key);
        Predicate<Person> containsPhone = s -> s.getPhone().contains(key);
        Predicate<Person> containsAdress = s -> s.getAddress().contains(key);
        var combine = containsName
                .or(containsAdress)
                .or(containsSurname)
                .or(containsPhone);
        var result = new ArrayList<Person>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}