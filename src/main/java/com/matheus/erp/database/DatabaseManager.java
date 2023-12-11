package com.matheus.erp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * FACADE: Abstract DB connection
 */
public class DatabaseManager {

    private Connection connection;

    // TODO: Move to an .env file
    private static final String HOST = "jdbc:mysql://127.0.0.1:3306";
    private static final String USER = "sail";
    private static final String PASSWORD = "password";
    private static final String DATABASE = "app";

   public DatabaseManager() throws SQLException {
       connection = DriverManager.getConnection(HOST+"/"+DATABASE, USER, PASSWORD);
   }

   public PreparedStatement prepareSelect(String table, HashMap<String, HashMap<String, String>> columnFilters)
   {
       return null;
   }

    public PreparedStatement prepareSelect(String table)
    {
        return prepareSelect(table, null);
    }
}
