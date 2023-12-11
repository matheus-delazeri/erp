package com.matheus.erp.database.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class ConnectionAdapter {

    protected final Connection connection;

    public ConnectionAdapter(Connection connection)
    {
        this.connection = connection;
    }

    public abstract PreparedStatement prepareSelect(String table, HashMap<String, String> columnFilters) throws SQLException;

}
