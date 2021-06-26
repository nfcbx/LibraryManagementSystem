package com.jdbc.records;

public class Record {
    public int user_id;
    public String user_name;


    public int book_id;
    public String book_name;
    public String book_author;
    public String publishing_house;

    public String borrow_time;
    public String return_time;

    public boolean is_overdue;

    public void user_toString() {
        System.out.println("Record{book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", book_author='" + book_author + '\'' +
                ", publishing_house='" + publishing_house + '\'' +
                ", borrow_time='" + borrow_time + '\'' +
                ", return_time='" + return_time + '\'' +
                ", is_overdue=" + is_overdue +
                '}');
    }

    public Record(int book_id, String book_name, String book_author, String publishing_house, String borrow_time, String return_time, boolean is_overdue) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.publishing_house = publishing_house;
        this.borrow_time = borrow_time;
        this.return_time = return_time;
        this.is_overdue = is_overdue;
    }

    public Record() {
    }

    public Record(int user_id, String user_name, int book_id, String book_name, String book_author, String publishing_house, String borrow_time, String return_time, boolean is_overdue) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.publishing_house = publishing_house;
        this.borrow_time = borrow_time;
        this.return_time = return_time;
        this.is_overdue = is_overdue;
    }
}
