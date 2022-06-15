package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book1 = new Book("Clean Code", 103);
        Book book2 = new Book("Booking", 200);
        Book book3 = new Book("Gram", 132);
        Book book4 = new Book("Mother", 321);
        Book[] books = new Book[4];
        books[0] = book1;
        books[1] = book2;
        books[2] = book3;
        books[3] = book4;
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName() + " - " + books[i].getPages());
        }
        System.out.println("Change books 0, 3.");
        Book book5 = books[0];
        books[0] = books[3];
        books[3] = book5;
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName() + " - " + books[i].getPages());
        }
        System.out.println("Print books with 'Clean Code' name.");
        for (int i = 0; i < books.length; i++) {
            if (books[i].getName().equals("Clean Code")) {
                System.out.println(books[i].getName() + " - " + books[i].getPages());
            }
        }
    }
}
