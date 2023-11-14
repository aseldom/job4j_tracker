package ru.job4j.tracker;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Data
public class Item {
    private int id;
    private String name;
    private LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(int id, String name, LocalDateTime time) {
        this.id = id;
        this.name = name;
        this.created = time;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '\'' + ", created=" + created.format(FORMATTER) + '}';
    }

}