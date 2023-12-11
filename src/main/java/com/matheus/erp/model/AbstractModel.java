package com.matheus.erp.model;

import java.util.HashMap;

/**
 * AbstractModel class serving as a foundation for specific models.
 * Defines common methods for managing data and interacting with resources.
 */
public abstract class AbstractModel {

    protected HashMap<String, String> data = new HashMap<>();

    /**
     * @return The AbstractResource associated with this model.
     */
    protected abstract AbstractResource getResource();

    /**
     * @return A Collection of model's items
     */
    public Collection getCollection() {
        return getResource().getCollection();
    }

    /**
     * Loads a given item by a given column
     *
     * @param id     The ID to load data for.
     * @param column The column name to filter by.
     * @return The loaded AbstractModel instance.
     */
    public AbstractModel load(String id, String column) {
        Collection collection = getCollection();
        collection.addColumnFilter(column, "= " + id);

        for (HashMap<String, String> row : collection) {
            data = row;
            return this;
        }

        return null;
    }

    /**
     * Loads data for this model based on a provided ID using the primary key column.
     *
     * @param id The ID to load data for.
     * @return The loaded AbstractModel instance.
     */
    public AbstractModel load(String id) {
        return load(id, getResource().getPkColumn());
    }

    /**
     * Retrieves data for a specified column.
     *
     * @param column The column name to retrieve data from.
     * @return The value associated with the column.
     */
    public String get(String column) {
        return data.get(column);
    }

    /**
     * Retrieves the ID for this model using the primary key column.
     *
     * @return The ID of this model.
     */
    public String getId() {
        return this.get(getResource().getPkColumn());
    }

    /**
     * Sets data for a specified column in the model.
     *
     * @param column The column name to set data for.
     * @param value  The value to set for the column.
     * @return The modified AbstractModel instance.
     */
    public AbstractModel set(String column, String value) {
        data.put(column, value);
        return this;
    }

    /**
     * Sets multiple data entries at once for the model.
     *
     * @param data The data represented as key-value pairs to set for the model.
     * @return The modified AbstractModel instance.
     */
    public AbstractModel set(HashMap<String, String> data) {
        for (String key : data.keySet()) {
            this.set(key, data.get(key));
        }
        return this;
    }

    /**
     * Saves the current state of the model data.
     *
     * @return True if the save operation is successful, false otherwise.
     */
    public boolean save() {
        try {
            data = getResource().save(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}