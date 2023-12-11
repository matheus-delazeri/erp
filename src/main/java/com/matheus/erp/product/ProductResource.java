package com.matheus.erp.product;

import com.matheus.erp.model.AbstractResource;
import com.matheus.erp.model.ModelFactory;

import java.util.HashMap;

public class ProductResource  extends AbstractResource {

    public ProductResource(String table, String pkColumn) {
        super(table, pkColumn);
    }

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
