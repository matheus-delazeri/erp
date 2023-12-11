package com.matheus.erp.database.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An abstract class defining methods for preparing statements on a database.
 */
public abstract class ConnectionAdapter {

    /**
     * The database connection used by this adapter.
     */
    protected final Connection connection;

    /**
     * Constructs a ConnectionAdapter with the provided database connection.
     *
     * @param connection The database connection to be used.
     */
    public ConnectionAdapter(Connection connection) {
        this.connection = connection;
    }

    /**
     * Prepares a SELECT statement with specified filters for a given table.
     *
     * @param table         Name of the table to select from.
     * @param columnFilters Filter expressions to be applied (e.g., "id = 1").
     * @return A prepared SELECT statement.
     * @throws SQLException If an SQL error occurs while preparing the statement.
     */
    public abstract PreparedStatement prepareSelect(String table, ArrayList<String> columnFilters) throws SQLException;

    /**
     * Prepares an INSERT statement for a specified table with data to be inserted.
     *
     * @param table Name of the table to insert into.
     * @param data  Data to be inserted, represented as key-value pairs (column_name -> value).
     * @return A prepared INSERT statement.
     * @throws SQLException If an SQL error occurs while preparing the statement.
     */
    public abstract PreparedStatement prepareInsert(String table, HashMap<String, String> data) throws SQLException;

    /**
     * Prepares an UPDATE statement for a specified table with data and a primary key.
     *
     * @param table    Name of the table to update.
     * @param pkColumn Name of the primary key column.
     * @param pk       Value of the primary key.
     * @param data     Data to be updated, represented as key-value pairs (column_name -> value).
     * @return A prepared UPDATE statement.
     * @throws SQLException If an SQL error
     */
    public abstract PreparedStatement prepareUpdate(String table, String pkColumn, String pk, HashMap<String, String> data) throws SQLException;
}
