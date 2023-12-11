package com.matheus.erp;

import com.matheus.erp.database.DatabaseManager;
import com.matheus.erp.model.ModelFactory;
import com.matheus.erp.product.ProductModel;

import java.sql.SQLException;
import java.util.HashMap;

public class App {
    public static DatabaseManager dbManager;

    public static void main(String[] args) throws SQLException {
        dbManager = new DatabaseManager();

        testCreateProduct();

        testRetrieveProduct();

        testUpdateProduct();

        testStockInformation();
    }

    public static void testCreateProduct() {
        ProductModel product = (ProductModel) ModelFactory.getModel("product.ProductModel");
        HashMap<String, String> productData = new HashMap<>(){{
            put("name", "New Product");
            put("sku", "new-product");
            put("description", "Product Description");
            put("price", "19.99");
        }};
        product.set(productData).save();
        System.out.println("Test 1: Created a new product");
    }

    public static void testRetrieveProduct() {
        ProductModel product = (ProductModel) ModelFactory.getSingleton("product.ProductModel");
        System.out.println("Test 2: Retrieved product: " + product.get("name"));
    }

    public static void testUpdateProduct() {
        ProductModel product = (ProductModel) ModelFactory.getSingleton("product.ProductModel");
        product.set("price", "25.00").save();
        System.out.println("Test 3: Updated product price");
    }

    public static void testStockInformation() {
        ProductModel product = (ProductModel) ModelFactory.getSingleton("product.ProductModel");
        System.out.println("Test 4: Product stock information - Quantity: " + product.getStock().get("qty"));
    }
}
