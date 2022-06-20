package ru.job4j.oop;

public class Train implements Vehicle {

    String model;

    public Train(String model) {
        this.model = model;
    }

    @Override
    public void move() {
        System.out.println("The train " + model + " runs on rails");
    }

}
