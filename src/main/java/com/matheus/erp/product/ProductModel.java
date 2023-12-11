package com.matheus.erp.product;

import com.matheus.erp.model.AbstractModel;
import com.matheus.erp.model.AbstractResource;

public class ProductModel extends AbstractModel {

    @Override
    protected AbstractResource getResource() {
        return new ProductResource("product", "product_id");
    }
}
