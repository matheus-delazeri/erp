package com.matheus.erp.model;

public abstract class AbstractModel {

    protected abstract AbstractResource getResource();

    public Collection getCollection()
    {
        return getResource().getCollection();
    }

}
