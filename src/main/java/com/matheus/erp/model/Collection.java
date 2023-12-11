package com.matheus.erp.model;

import com.matheus.erp.App;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Collection class representing a collection of database records.
 * Implements Iterable to allow iteration over the collection.
 */
public class Collection implements Iterable<HashMap<String, String>> {

    private final String table;
    private final ArrayList<String> columnFilters = new ArrayList<>();

    /**
     * Constructor for Collection, initializing with the specified table.
     *
     * @param table The name of the database table.
     */
    public Collection(String table) {
        this.table = table;
    }

    /**
     * Adds a filter for a column using a comparative expression.
     *
     * @param column     The name of the column to filter.
     * @param expression The comparative expression to apply (e.g., "> 10", "= 1", "< 20").
     * @return The modified Collection instance with the added filter.
     */
    public Collection addColumnFilter(String column, String expression) {
        columnFilters.add(column + " " + expression);
        return this;
    }

    /**
     * Provides an iterator to iterate over the collection of records.
     *
     * @return An Iterator instance for iterating over records.
     */
    @Override
    public Iterator<HashMap<String, String>> iterator() {
        return new Iterator<>() {
            ResultSet results;

            {
                fetchResults();
            }

            private void fetchResults() {
                results = App.dbManager.select(table, columnFilters);
            }

            @Override
            public boolean hasNext() {
                try {
                    return results.next();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            public HashMap<String, String> next() {
                HashMap<String, String> columnValues = new HashMap<>();

                try {
                    ResultSetMetaData metaData = results.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        String columnValue = results.getString(i);
                        columnValues.put(columnName, columnValue);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return columnValues;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

