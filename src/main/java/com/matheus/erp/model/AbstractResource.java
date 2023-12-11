package com.matheus.erp.model;

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

    public Collection getCollection()
    {
        return new Collection(table);
    }

}
