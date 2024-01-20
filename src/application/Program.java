package application;

import db.DB;

import java.sql.Connection;

public class Program {
    public static void main(String[] args) {
        System.out.println("HELLO WORLD!");

        Connection connection = DB.startConnection();

        DB.closeConnection();
    }
}
