package ru.job4j.stream;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        return stream.flatMap(a -> a.subjects().stream())
                .map(Subject::score)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream.map(a -> new Tuple(a.name(), a.subjects()
                        .stream()
                        .map(Subject::score)
                        .mapToInt(Integer::intValue)
                        .average()
                        .orElse(0)))
                .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream.flatMap(a -> a.subjects().stream())
                .collect(Collectors.groupingBy(Subject::name, LinkedHashMap::new,
                        Collectors.averagingDouble(Subject::score)))
                .entrySet().stream()
                .map(a -> new Tuple(a.getKey(), a.getValue()))
                .collect(Collectors.toList());
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream.map(a -> new Tuple(a.name(), a.subjects()
                        .stream()
                        .map(Subject::score)
                        .mapToInt(Integer::intValue)
                        .sum()))
                .max(Comparator.comparing(Tuple::score))
                .orElse(null);
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream
                .flatMap(a -> a.subjects().stream())
                .collect(Collectors.groupingBy(Subject::name,
                        Collectors.summingDouble(Subject::score)))
                .entrySet().stream()
                .map(a -> new Tuple(a.getKey(), a.getValue()))
                .max(Comparator.comparing(Tuple::score))
                .orElse(null);
    }
}