package com.matheus.erp.model;

import com.matheus.erp.App;

import java.util.HashMap;

/**
 * AbstractResource class representing a resource (database table) for models.
 * Provides an interface to handle table-related operations.
 */
public class AbstractResource {

    private final String table;
    private final String pkColumn;

    /**
     * Constructor for AbstractResource.
     *
     * @param table    Name of the database table.
     * @param pkColumn Name of the primary key column.
     */
    public AbstractResource(String table, String pkColumn) {
        this.table = table;
        this.pkColumn = pkColumn;
    }

    /**
     * Gets the name of the database table associated with this resource.
     *
     * @return The name of the table.
     */
    public String getTable() {
        return table;
    }

    /**
     * Gets the name of the primary key column for the table.
     *
     * @return The name of the primary key column.
     */
    public String getPkColumn() {
        return pkColumn;
    }

    /**
     * Creates and returns a Collection associated with this resource's table.
     *
     * @return A Collection instance for the table.
     */
    public Collection getCollection() {
        return new Collection(table);
    }

    /**
     * Saves the provided data into the associated table.
     * Performs an update if the primary key value exists in the data; otherwise, performs an insert.
     *
     * @param data The data to save, represented as key-value pairs.
     * @return The modified data after saving.
     */
    public HashMap<String, String> save(HashMap<String, String> data) {
        if (data.get(pkColumn) != null) {
            return App.dbManager.update(table, pkColumn, data.get(pkColumn), data);
        }

        return App.dbManager.insert(table, pkColumn, data);
    }
}
