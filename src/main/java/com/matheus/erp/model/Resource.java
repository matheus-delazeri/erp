package com.matheus.erp.model;

public abstract class Resource {

    private final String table;
    private final String pkColumn;

    public Resource(String table, String pkColumn)
    {
        this.table = table;
        this.pkColumn = pkColumn;
    }

    public String getTable() {
        return table;
    }


}
