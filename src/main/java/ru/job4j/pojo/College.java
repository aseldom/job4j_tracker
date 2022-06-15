package ru.job4j.pojo;

public class College {

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Petr Arsentev");
        student.setGroup("Biolog");
        student.setDate("2022.01.01");

        System.out.println("Студент - " + student.getName() + ", группа - " + student.getGroup() + ", дата: "
        + student.getDate());
    }
}
