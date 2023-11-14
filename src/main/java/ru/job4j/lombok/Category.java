package ru.job4j.lombok;

import lombok.*;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @EqualsAndHashCode.Include
    @NonNull
    private int id;
    @Setter
    private String name;
}