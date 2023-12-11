package com.matheus.erp.product;

import com.matheus.erp.model.AbstractResource;
import com.matheus.erp.model.ModelFactory;

import java.util.HashMap;

/**
 * Handle database-related product operations.
 */
public class ProductResource extends AbstractResource {

    /**
     * Constructor for ProductResource.
     *
     * @param table    Name of the database table associated with products.
     * @param pkColumn Name of the primary key column for the product table.
     */
    public ProductResource(String table, String pkColumn) {
        super(table, pkColumn);
    }

    /**
     * Overrides the save method to auto-generate the stock register related
     * to the saved product.
     *
     * @param data The data to save, represented as key-value pairs.
     * @return The modified data after saving.
     */
    @Override
    public HashMap<String, String> save(HashMap<String, String> data) {
        HashMap<String, String> product = super.save(data);

        if (product != null) {
            ModelFactory.getModel("product.stock.StockModel")
                    .set("product_id", product.get(getPkColumn()))
                    .set("qty", "0")
                    .save();
        }

        return product;
    }
}
