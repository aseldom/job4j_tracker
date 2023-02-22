package ru.job4j.early;

import static java.lang.Character.*;

public class PasswordValidator {
    private final static String[] PATTERN = {"qwerty", "12345", "password", "admin", "user"};
    private static boolean isUpper;
    private static boolean isLower;
    private static boolean isDigit;
    private static boolean isSpecial;

    public static String validate(String password) {
        isUpper = false;
        isLower = false;
        isDigit = false;
        isSpecial = false;

        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }

        char[] chars = password.toCharArray();

        checkSymbols(chars);

        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }

        if (!isUpper) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!isLower) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!isDigit) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!isSpecial) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
        if (isPattern(PATTERN, password)) {
            throw new IllegalArgumentException("Password shouldn't contain substrings: qwerty, 12345, password, admin, user");
        }
        return password;
    }

    public static void checkSymbols(char[] chars) {
        for (char c : chars) {
            if (isUpperCase(c)) {
                isUpper = true;
            }
            if (isLowerCase(c)) {
                isLower = true;
            }
            if (isDigit(c)) {
                isDigit = true;
            }
            if (!isDigit(c) && !isLetter(c)) {
                isSpecial = true;
            }
        }
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