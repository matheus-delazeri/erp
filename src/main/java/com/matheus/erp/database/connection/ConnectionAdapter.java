package com.matheus.erp.database.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class ConnectionAdapter {

    protected final Connection connection;

    public ConnectionAdapter(Connection connection)
    {
        this.connection = connection;
    }

    public abstract PreparedStatement prepareSelect(String table, ArrayList<String> columnFilters) throws SQLException;
    public abstract PreparedStatement prepareInsert(String table, HashMap<String, String> data) throws SQLException;
    public abstract PreparedStatement prepareUpdate(String table, String pkColumn, String pk, HashMap<String, String> data) throws SQLException;

}
