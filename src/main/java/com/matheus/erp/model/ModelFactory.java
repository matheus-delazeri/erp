package com.matheus.erp.model;

public class ModelFactory {

    public static AbstractModel getModel(String modelPackage) {
        try {
            String className = "com.matheus.erp." + modelPackage;

            Class<?> modelClass = Class.forName(className);

            return (AbstractModel) modelClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
