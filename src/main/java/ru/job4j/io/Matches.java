package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            System.out.println("Число оставшихся спичек: " + count);
            System.out.println(player + " введите число от 1 до " + ((count >= 2) ? 3 : count) + ": ");
            int matches = Integer.parseInt(input.nextLine());
            if (matches < 1 || matches > 3) {
                System.out.println("Ошибка, неправильный ввод");
            } else if (count - matches < 0) {
                System.out.println("Необходимо ввести число спичек не больше: " + count);
            } else {
                count -= matches;
                turn = !turn;
            }
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}