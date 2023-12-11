package com.matheus.erp.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Collection implements Iterable {

    private final String table;
    /**
     * "column_name" => [ "{filter_type}" => "value" ]
     */
    private HashMap<String, HashMap<String, String>> columnFilters;

    public Collection(String table)
    {
        this.table = table;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            long currentIt = 0L;
            ResultSet results;

            {
                fetchResults();
            }

            private void fetchResults()
            {

            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public HashMap<String, String> next() {
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
