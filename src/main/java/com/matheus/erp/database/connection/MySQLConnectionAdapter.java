package com.matheus.erp.database.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// TODO: Fix SQL Injection
public class MySQLConnectionAdapter extends ConnectionAdapter {

    public MySQLConnectionAdapter(Connection connection) {
        super(connection);
    }

    @Override
    public PreparedStatement prepareSelect(String table, ArrayList<String>columnFilters) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM " + table + " ");
        if (columnFilters != null && !columnFilters.isEmpty()) {
            queryBuilder.append("WHERE ");
            for (String filterExpression : columnFilters) {
                // TODO: Accept "OR" filters also
                queryBuilder.append(filterExpression).append(" AND ");
            }
        }

        queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());

        return connection.prepareStatement(queryBuilder.toString());
    }

    @Override
    public PreparedStatement prepareInsert(String table, HashMap<String, String> data) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
        queryBuilder.append(table).append(" (");

        for (String column : data.keySet()) {
            queryBuilder.append(column).append(", ");
        }
        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
        queryBuilder.append(") VALUES (");

        int dataCount = data.size();
        queryBuilder.append("?, ".repeat(dataCount));
        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length()); // Removing the extra comma and space
        queryBuilder.append(")");

        PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());

        int paramIndex = 1;
        for (String value : data.values()) {
            preparedStatement.setString(paramIndex++, value);
        }

        return preparedStatement;
    }

    @Override
    public PreparedStatement prepareUpdate(String table, String pkColumn, String pk, HashMap<String, String> data) throws SQLException {

        StringBuilder queryBuilder = new StringBuilder("UPDATE ");
        queryBuilder.append(table).append(" SET ");

        for (String column : data.keySet()) {
            queryBuilder.append(column).append(" = ?, ");
        }
        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
        queryBuilder.append(" WHERE ").append(pkColumn).append(" = ?"); // Removing the extra comma and space

        PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());

        int paramIndex = 1;
        for (String value : data.values()) {
            preparedStatement.setString(paramIndex++, value);
        }
        preparedStatement.setString(paramIndex, pk);

        return preparedStatement;
    }
}
