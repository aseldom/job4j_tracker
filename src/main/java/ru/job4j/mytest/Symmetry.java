package ru.job4j.mytest;

public class Symmetry {
    public static boolean check(int i) {
        String number = String.valueOf(i);
        for (int k = 0; k < number.length() / 2; k++) {
            if (number.charAt(k) == number.charAt(number.length() - k - 1)) {
                return false;
            }
        }
        return true;
    }
    //sdaf
}