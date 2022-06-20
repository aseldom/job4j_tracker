package ru.job4j.poly;

public class Bus implements Transport {

    @Override
    public void drive() {
        System.out.println("Drive");
    }

    @Override
    public void passengers(int peoples) {
        System.out.println("Passengers = " + peoples);
    }

    @Override
    public int refuel(int fuel) {
        int cost = 32;
        return fuel * cost;
    }
}
