package com.matheus.erp.product.stock;

import com.matheus.erp.model.AbstractModel;
import com.matheus.erp.model.AbstractResource;

public class StockModel extends AbstractModel {
    @Override
    protected AbstractResource getResource() {
        return new StockResource("stock", "stock_id");
    }
}
