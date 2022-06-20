package ru.job4j.oop;

public class Airplane implements Vehicle {
    String model;

    public Airplane(String model) {
        this.model = model;
    }

    @Override
    public void move() {
        System.out.println("The plane " + model + " flies through the air");
    }

    public static void main(String[] args) {
        Vehicle airplane1 = new Airplane("TU-154");
        Vehicle airplane2 = new Airplane("IL-96");
        Vehicle bus1 = new Bus("Mercedes");
        Vehicle bus2 = new Bus("Ikarus");
        Vehicle train1 = new Train("K88");
        Vehicle train2 = new Train("Kilomner");
        Vehicle[] vehicles = {airplane1, airplane2, bus1, bus2, train1, train2};
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
        }
    }

}
