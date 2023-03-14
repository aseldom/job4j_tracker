package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortTest {
    @Test
    void itemAscByNameSort() {
        List<Item> items = Arrays.asList(
                new Item("Name aaaaaaa"),
                new Item("Name bbbbbbb"),
                new Item("Name wwwwwww"),
                new Item("Name aaaaaaa")
        );
        List<Item> expected = Arrays.asList(
                new Item("Name aaaaaaa"),
                new Item("Name aaaaaaa"),
                new Item("Name bbbbbbb"),
                new Item("Name wwwwwww")
        );
        items.sort(new ItemAscByName());
        assertThat(items).isEqualTo(expected);
    }

    @Test
    void itemDescByNameSort() {
        List<Item> items = Arrays.asList(
                new Item("Name aaaaaaa"),
                new Item("Name bbbbbbb"),
                new Item("Name wwwwwww"),
                new Item("Name aaaaaaa")
        );
        List<Item> expected = Arrays.asList(
                new Item("Name wwwwwww"),
                new Item("Name bbbbbbb"),
                new Item("Name aaaaaaa"),
                new Item("Name aaaaaaa")
        );
        items.sort(new ItemDescByName());
        assertThat(items).isEqualTo(expected);
    }
}