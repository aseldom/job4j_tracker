package ru.job4j.tracker;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoTest {
    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction rep = new ReplaceAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Edit item ===" + ln + "Заявка изменена успешно." + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(replacedName);
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Deleted item"));
        int idItem = tracker.findAll().get(0).getId();
        DeleteAction deleteAction = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(idItem);

        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Delete item ===" + ln + "Заявка удалена успешно." + ln);
        assertThat(tracker.findAll().size()).isEqualTo(0);
    }

    @Test
    public void whenFindByIdAction() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        String name = "Simply item";
        tracker.add(new Item(name));
        int idItem = tracker.findAll().get(0).getId();
        FindByIdAction findByIdAction = new FindByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(idItem);

        findByIdAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find item by id ===" + ln + tracker.findById(idItem) + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(name);
    }

    @Test
    public void whenFindByNameAction() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        String name = "Simply item";
        Item item = tracker.add(new Item(name));
        FindByNameAction findByNameAction = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name);

        findByNameAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo("=== Find items by name ===" + ln + item + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(name);
    }

}