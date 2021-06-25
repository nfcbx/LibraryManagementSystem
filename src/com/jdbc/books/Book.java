package com.jdbc.books;

public class Book {
    public int book_id;
    public String book_name;
    public String book_author;
    public String publishing_house;

    public Book() {
    }

    public Book(int book_id, String book_name, String book_author, String publishing_house) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.publishing_house = publishing_house;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", book_author='" + book_author + '\'' +
                ", publishing_house='" + publishing_house + '\'' +
                '}';
    }
}
