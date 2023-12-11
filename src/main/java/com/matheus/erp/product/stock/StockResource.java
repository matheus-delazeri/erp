package com.matheus.erp.product.stock;

import com.matheus.erp.model.AbstractResource;

/**
 * Handle database-related stock operations.
 */
public class StockResource extends AbstractResource {

    /**
     * Constructor for StockResource.
     *
     * @param table    Name of the database table associated with stocks.
     * @param pkColumn Name of the primary key column for the stock table.
     */
    public StockResource(String table, String pkColumn) {
        super(table, pkColumn);
    }
}
