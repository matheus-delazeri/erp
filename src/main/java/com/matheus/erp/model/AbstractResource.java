package com.matheus.erp.model;

import com.matheus.erp.App;

import java.util.HashMap;

public class AbstractResource {

    private final String table;
    private final String pkColumn;

    public AbstractResource(String table, String pkColumn)
    {
        this.table = table;
        this.pkColumn = pkColumn;
    }

    public String getTable() {
        return table;
    }

    public String getPkColumn() {
        return pkColumn;
    }

    public Collection getCollection()
    {
        return new Collection(table);
    }

    public HashMap<String, String> save(HashMap<String, String> data)
    {
        if (data.get(pkColumn) != null) {
            return App.dbManager.update(table, pkColumn, data.get(pkColumn), data);
        }

        return App.dbManager.insert(table, pkColumn, data);
    }

}
