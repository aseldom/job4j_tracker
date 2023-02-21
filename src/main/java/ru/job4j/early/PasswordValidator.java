package ru.job4j.early;

import static java.lang.Character.*;

public class PasswordValidator {

    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        char[] chars = password.toCharArray();

        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        if (!isUpperCaseLatter(chars)) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!islowerCaseLatter(chars)) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!isOneFigure(chars)) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!isOneSpecial(chars)) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
        if (isPattern(new String[] {"qwerty", "12345", "password", "admin", "user"}, password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }
        return password;
    }

    public static boolean isUpperCaseLatter(char[] chars) {
        for (char c : chars) {
            if (isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean islowerCaseLatter(char[] chars) {
        for (char c : chars) {
            if (isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOneFigure(char[] chars) {
        for (char c : chars) {
            if (isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOneSpecial(char[] chars) {
        for (char c : chars) {
            if (!isDigit(c) && !isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPattern(String[] patterns, String password) {
        String pass = password.toLowerCase();
        for (String pattern : patterns) {
            if (pass.contains(pattern)) {
                return true;
            }
        }
        return false;
    }
}