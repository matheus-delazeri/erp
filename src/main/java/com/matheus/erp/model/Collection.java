package com.matheus.erp.model;

import com.matheus.erp.App;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

// Iterator
public class Collection implements Iterable<HashMap<String, String>> {

    private final String table;
    /**
     * e.g: "column_name" => "> 10"
     */
    private final HashMap<String, String> columnFilters = new HashMap<>();

    public Collection(String table) {
        this.table = table;
    }

    /**
     * @param expression Can be used for comparative filtering. e.g: ">", "=", "<"
     */
    public Collection addColumnFilter(String column, String expression) {
        columnFilters.put(column, expression);

        return this;
    }


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
