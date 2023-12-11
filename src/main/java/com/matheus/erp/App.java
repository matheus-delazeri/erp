package com.matheus.erp;

import com.matheus.erp.database.DatabaseManager;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static DatabaseManager dbManager;

    public static void main( String[] args ) throws SQLException {
        dbManager = new DatabaseManager();
    }
}
