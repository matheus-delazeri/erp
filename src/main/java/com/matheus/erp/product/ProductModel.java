package com.matheus.erp.product;

import com.matheus.erp.model.AbstractModel;
import com.matheus.erp.model.AbstractResource;
import com.matheus.erp.model.ModelFactory;
import com.matheus.erp.product.stock.StockModel;

/**
 * Handle product operations.
 */
public class ProductModel extends AbstractModel {

    @Override
    protected AbstractResource getResource() {
        return new ProductResource("product", "product_id");
    }

    /**
     * Retrieve the stock model of the loaded item.
     *
     * @return The stock model of the current loaded product.
     */
    public StockModel getStock()
    {
        return (StockModel) ModelFactory.getModel("product.stock.StockModel")
                .load(getId(), "product_id");
    }
}
