package ru.job4j.oop;

public class Error {
    boolean active;
    int status;
    String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void out() {
        System.out.println("active = " + active);
        System.out.println("status = " + status);
        System.out.println("message = " + message);
    }

    public static void main(String[] args) {
        Error error1 = new Error();
        Error error2 = new Error(true, 256, "default error");
        Error error3 = new Error(false, 2, "print error");
        Error error4 = new Error(true, 6, "out error");
        error1.out();
        error2.out();
        error3.out();
        error4.out();
    }
}
