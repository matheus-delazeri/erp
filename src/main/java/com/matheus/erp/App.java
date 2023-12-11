package com.matheus.erp;

import com.matheus.erp.database.DatabaseManager;
import com.matheus.erp.model.AbstractModel;
import com.matheus.erp.model.ModelFactory;
import com.matheus.erp.product.ProductModel;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Hello world!
 */
public class App {
    public static DatabaseManager dbManager;

    public static void main(String[] args) throws SQLException {
        dbManager = new DatabaseManager();
        ProductModel product = (ProductModel) ModelFactory.getModel("product.ProductModel");
        HashMap<String, String> productData = new HashMap<>(){{
            put("name", "New Product");
            put("sku", "new-product");
            put("description", "Product Description");
            put("price", "19.99");
        }};

        product.set(productData).save();

        ProductModel product1 = (ProductModel) ModelFactory.getSingleton("product.ProductModel");

        System.out.println(product.get("name"));

    }
}
