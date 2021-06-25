package com.jdbc.users;

public class User {
    public int user_index;
    public int user_id;
    public String user_name;
    public String user_password;

    public User(int user_index, int user_id, String user_name, String user_password) {
        this.user_index = user_index;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "user_index=" + user_index +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';
    }
}
