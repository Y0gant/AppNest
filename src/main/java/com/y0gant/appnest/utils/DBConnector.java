package com.y0gant.appnest.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final Dotenv env = Dotenv.load();

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        String url = env.get("DB_URL");
        String user = env.get("DB_USER");
        String password = env.get("DB_PASSWORD");

        return DriverManager.getConnection(url, user, password);
    }

}
