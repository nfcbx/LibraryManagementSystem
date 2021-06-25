package com;

import com.jdbc.books.Book;
import com.jdbc.users.User;

import java.util.Vector;

public class Sout {
    public static void soutVectorBooks(Vector<Book> bookVector) {
        for (int i = 0; i < bookVector.size(); i++) {
            System.out.println("id:" + bookVector.get(i).book_id + "\tname:" + bookVector.get(i).book_name);
        }
    }

    public static void soutVectorUsrs(Vector<User> userVector) {
        for (int i = 0; i < userVector.size(); i++) {
            System.out.println("id:" + userVector.get(i).user_id + "\tname:" + userVector.get(i).user_name);
        }
    }

}
