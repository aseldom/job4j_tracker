package ru.job4j.lombok;

import lombok.*;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @EqualsAndHashCode.Include
    final private int id;
    @Setter
    private String name;
}