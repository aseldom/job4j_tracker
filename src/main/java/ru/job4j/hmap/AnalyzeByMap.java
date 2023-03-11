package ru.job4j.hmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double sum = 0;
        double number = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sum += subject.score();
                number++;
            }
        }
        return sum / number;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> listPupil = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sum = 0;
            double number = 0;
            for (Subject subject: pupil.subjects()) {
                sum += subject.score();
                number++;
            }
            listPupil.add(new Label(pupil.name(), sum / number));
        }
        return listPupil;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> list = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                String name = subject.name();
                map.put(name, subject.score() + map.getOrDefault(name, 0));
            }
        }
        int numPupils = pupils.size();
        for (String name : map.keySet()) {
            list.add(new Label(name, map.get(name) / numPupils));
        }
        return list;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sum = 0;
            for (Subject subject : pupil.subjects()) {
                sum += subject.score();
            }
            list.add(new Label(pupil.name(), sum));
        }
        list.sort(Comparator.naturalOrder());
        return list.get(list.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> list = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                String name = subject.name();
                map.put(name, subject.score() + map.getOrDefault(name, 0));
            }
        }
        for (String name : map.keySet()) {
            list.add(new Label(name, map.get(name)));
        }
        list.sort(Comparator.naturalOrder());
        return list.get(pupils.size() - 1);
    }
}