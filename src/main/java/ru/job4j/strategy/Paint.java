package ru.job4j.strategy;

public class Paint {
    public void drawing(Shape shape) {
        System.out.print(shape.draw());
    }

    public static void main(String[] args) {
        Paint context = new Paint();
        context.drawing(new Triangle());
        context.drawing(new Square());
    }
}