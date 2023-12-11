package com.matheus.erp.model;

import java.util.HashMap;

public abstract class AbstractModel {

    protected HashMap<String, String> data = new HashMap<>();
    protected abstract AbstractResource getResource();

    public Collection getCollection()
    {
        return getResource().getCollection();
    }

    public AbstractModel load(String id, String column)
    {
        Collection collection = getCollection();
        collection.addColumnFilter(column, "= " + id);

        for (HashMap<String, String> row : collection) {
            data = row;
            return this;
        }

        return null;
    }

    public AbstractModel load(String id)
    {
        return load(id, getResource().getPkColumn());
    }

    public String get(String column)
    {
        return data.get(column);
    }

    public String getId()
    {
        return this.get(getResource().getPkColumn());
    }

    public AbstractModel set(String column, String value)
    {
        data.put(column, value);

        return this;
    }

    public AbstractModel set(HashMap<String, String> data)
    {
        for (String key : data.keySet()) {
            this.set(key, data.get(key));
        }

        return this;
    }

    public boolean save()
    {
        try {
            data = getResource().save(data);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
