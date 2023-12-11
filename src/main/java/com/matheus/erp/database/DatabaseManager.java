package com.matheus.erp.database;

import com.matheus.erp.database.connection.ConnectionAdapter;
import com.matheus.erp.database.connection.MySQLConnectionAdapter;

import java.sql.*;
import java.util.HashMap;

/**
 * FACADE: Abstract DB connection
 */
public class DatabaseManager {

    private final ConnectionAdapter adapter;

    // TODO: Move to an .env file
    private static final String HOST = "jdbc:mysql://127.0.0.1:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE = "erp";

    public DatabaseManager(ConnectionAdapter adapter) {
        this.adapter = adapter;
    }

    public DatabaseManager() throws SQLException {
        Connection connection = DriverManager.getConnection(HOST + "/" + DATABASE, USER, PASSWORD);
        // By default, use MySQL adapter
        adapter = new MySQLConnectionAdapter(connection);
    }


    public ResultSet select(String table, HashMap<String, String> columnFilters) {
        try {
            PreparedStatement statement = adapter.prepareSelect(table, columnFilters);

            return statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
