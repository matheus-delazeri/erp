package com.matheus.erp.database.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

public abstract class ConnectionAdapter {

    private final Connection connection;

    public ConnectionAdapter(Connection connection)
    {
        this.connection = connection;
    }

    abstract PreparedStatement prepareSelect(String table, HashMap<String, HashMap<String, String>> columnFilters);

}
