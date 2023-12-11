package com.matheus.erp.database.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

public class MySQLConnectionAdapter  extends ConnectionAdapter {

    public MySQLConnectionAdapter(Connection connection) {
        super(connection);
    }

    @Override
    PreparedStatement prepareSelect(String table, HashMap<String, HashMap<String, String>> columnFilters) {
        return null;
    }
}
