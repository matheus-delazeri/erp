package com.matheus.erp.product;

import com.matheus.erp.model.AbstractModel;
import com.matheus.erp.model.AbstractResource;
import com.matheus.erp.model.ModelFactory;
import com.matheus.erp.product.stock.StockModel;

public class ProductModel extends AbstractModel {

    @Override
    protected AbstractResource getResource() {
        return new ProductResource("product", "product_id");
    }

    public StockModel getStock()
    {
        return (StockModel) ModelFactory.getModel("product.stock.StockModel")
                .load(getId(), "product_id");
    }
}
