package com.matheus.erp.database;

import com.matheus.erp.database.connection.ConnectionAdapter;
import com.matheus.erp.database.connection.MySQLConnectionAdapter;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class manages database operations by providing methods for selecting, inserting, and updating data.
 */
public class DatabaseManager {

    private final ConnectionAdapter adapter;

    // TODO: Move to an .env file
    private static final String HOST = "jdbc:mysql://127.0.0.1:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE = "erp";

    /**
     * Constructor to initialize DatabaseManager with a provided ConnectionAdapter.
     *
     * @param adapter The ConnectionAdapter to use for database operations.
     */
    public DatabaseManager(ConnectionAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Constructor that establishes a connection and sets up a default adapter (MySQLConnectionAdapter).
     *
     * @throws SQLException If a database connection error occurs.
     */
    public DatabaseManager() throws SQLException {
        Connection connection = DriverManager.getConnection(HOST + "/" + DATABASE, USER, PASSWORD);
        // By default, use MySQL adapter
        adapter = new MySQLConnectionAdapter(connection);
    }

    /**
     * Retrieves data from the database based on the provided table and column filters.
     *
     * @param table         Name of the table from which to retrieve data.
     * @param columnFilters Filter expressions to apply.
     * @return ResultSet containing the retrieved data.
     */
    public ResultSet select(String table, ArrayList<String> columnFilters) {
        try {
            PreparedStatement statement = adapter.prepareSelect(table, columnFilters);
            return statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Inserts data into the specified table.
     *
     * @param table    Name of the table to insert data into.
     * @param pkColumn Primary key column name.
     * @param data     Data to be inserted, represented as key-value pairs.
     * @return HashMap containing the inserted data.
     */
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

    /**
     * Updates data in the specified table based on the provided primary key and data.
     *
     * @param table    Name of the table to update.
     * @param pkColumn Primary key column name.
     * @param pk       Value of the primary key.
     * @param data     Data to update, represented as key-value pairs.
     * @return HashMap containing the updated data.
     */
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
