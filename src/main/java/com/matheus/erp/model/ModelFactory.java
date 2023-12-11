package com.matheus.erp.model;

import java.util.HashMap;
import java.util.Map;

public class ModelFactory {
    private static Map<String, AbstractModel> singletonModels = new HashMap<>();

    public static AbstractModel getModel(String modelPackage) {
        try {
            String className = "com.matheus.erp." + modelPackage;

            Class<?> modelClass = Class.forName(className);
            AbstractModel model = (AbstractModel) modelClass.newInstance();

            singletonModels.put(modelPackage, model);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static AbstractModel getSingleton(String modelPackage) {
        AbstractModel singleton = singletonModels.get(modelPackage);

        if (singleton == null) {
            singleton = getModel(modelPackage);
            singletonModels.put(modelPackage, singleton);
        }

        return singleton;
    }
}
