package com.matheus.erp.database.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySQLConnectionAdapter  extends ConnectionAdapter {

    public MySQLConnectionAdapter(Connection connection) {
        super(connection);
    }

    @Override
    public PreparedStatement prepareSelect(String table, HashMap<String, String> columnFilters) throws SQLException {
            StringBuilder query = new StringBuilder("SELECT * FROM " + table + " ");
            if (columnFilters != null && !columnFilters.isEmpty()) {
                query.append("WHERE ");
                for (Map.Entry<String, String> filter : columnFilters.entrySet()) {
                    // TODO: Fix SQL Injection
                    query.append(filter.getKey());
                    query.append(filter.getValue());
                }
            }

            return connection.prepareStatement(query.toString());
    }
}
