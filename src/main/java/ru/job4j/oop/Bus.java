package ru.job4j.oop;

public class Bus implements Vehicle {

    private String model;

    public Bus(String model) {
        this.model = model;
    }

    @Override
    public void move() {
        System.out.println("The bus " + model + " rides on the roads");
    }

    public void signal() {
        System.out.println("The bus doing \"beep - beep!\"");
    }

}
