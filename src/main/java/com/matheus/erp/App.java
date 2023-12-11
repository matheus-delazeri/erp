package com.matheus.erp;

import com.matheus.erp.database.DatabaseManager;
import com.matheus.erp.model.AbstractModel;
import com.matheus.erp.model.ModelFactory;

import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static DatabaseManager dbManager;

    public static void main(String[] args) throws SQLException {
        dbManager = new DatabaseManager();
        AbstractModel product = ModelFactory.getModel("product.ProductModel");

        System.out.println(product.load("1").get("name"));

    }
}
