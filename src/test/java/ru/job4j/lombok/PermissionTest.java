package ru.job4j.lombok;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PermissionTest {

    @Test
    public void whenAddIdNameRulesAndGetTheir() {
        String expected = "Permission(id=1, name=Name, rules=[First rules])";
        Permission permission = Permission.of()
                .id(1)
                .name("Name")
                .rules("First rules")
                .build();
        assertThat(permission.toString()).isEqualTo(expected);
    }

    @Test
    public void whenAddNothingAndGetTheir() {
        String expected = "Permission(id=0, name=null, rules=[])";
        Permission permission = Permission.of()
                .build();
        assertThat(permission.toString()).isEqualTo(expected);
    }

}