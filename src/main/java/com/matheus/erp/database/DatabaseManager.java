package com.matheus.erp.database;

import com.matheus.erp.database.connection.ConnectionAdapter;
import com.matheus.erp.database.connection.MySQLConnectionAdapter;

import java.sql.*;
import java.util.ArrayList;
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


    public ResultSet select(String table, ArrayList<String> columnFilters) {
        try {
            PreparedStatement statement = adapter.prepareSelect(table, columnFilters);

            return statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HashMap<String, String> insert(String table, String pkColumn, HashMap<String, String> data) {
        try {
            PreparedStatement statement = adapter.prepareInsert(table, data);
            statement.executeUpdate();
            if (data.get(pkColumn) == null) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    data.put(pkColumn, generatedKeys.getString(1));
                }
            }

            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HashMap<String, String> update(String table, String pkColumn, String pk, HashMap<String, String> data) {
        try {
            PreparedStatement statement = adapter.prepareUpdate(table, pkColumn, pk, data);
            statement.executeUpdate();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
