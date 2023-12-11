package com.matheus.erp;

import com.matheus.erp.database.DatabaseManager;
import com.matheus.erp.model.Collection;
import com.matheus.erp.product.ProductModel;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static DatabaseManager dbManager;

    public static void main( String[] args ) throws SQLException {
        dbManager = new DatabaseManager();
        ProductModel product = new ProductModel();
        Collection collection = product.getCollection();

        for (HashMap<String, String> row : collection) {
            System.out.println(row.get("name"));
        }
    }
}
