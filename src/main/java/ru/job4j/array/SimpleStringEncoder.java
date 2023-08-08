package ru.job4j.array;

public class SimpleStringEncoder {
    public static void main(String[] args) {
        SimpleStringEncoder.encode("aaaaaaaaaaaabbbcdddd");
    }

    public static String encode(String input) {
        char symbol = input.charAt(0);
        int counter = 1;
        String result = String.valueOf(symbol);
        for (int i = 1; i < input.length(); i++) {
            if (symbol == input.charAt(i)) {
                counter++;
                if (i == input.length() - 1) {
                    result += counter;
                }
            } else {
                result = (counter != 1) ? result + counter : result;
                symbol = input.charAt(i);
                result += String.valueOf(symbol);
                counter = 1;
            }
        }
        System.out.println(result);
        return result;
    }
}