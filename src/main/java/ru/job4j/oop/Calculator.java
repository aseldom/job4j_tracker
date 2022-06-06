package ru.job4j.oop;

public class Calculator {

    static private int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public static int minus(int y) {
        return y - x;
    }

    public double divide(int y) {
        return (double) y / x;
    }

    public int multiply(int y) {
        return x * y;
    }

    public double sumAllOperation(int y) {
        return sum(y) + minus(y) + divide(y) + multiply(y);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(sum(3));
        System.out.println(minus(3));
        System.out.println(calc.divide(3));
        System.out.println(calc.multiply(3));
        System.out.println(calc.sumAllOperation(3));

    }
}