package com.matheus.erp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * ModelFactory class responsible for creating and managing instances of AbstractModel.
 * Provides methods to retrieve models and manage singleton instances.
 */
public class ModelFactory {
    // Map to store singleton instances of AbstractModel associated with modelPackage
    private static Map<String, AbstractModel> singletonModels = new HashMap<>();

    /**
     * Retrieves an instance of AbstractModel based on the provided modelPackage.
     *
     * @param modelPackage The package name of the model class.
     *                     Relative to: 'com.matheus.erp'. e.g: 'product.ProductModel'
     * @return An instance of AbstractModel for the specified modelPackage.
     */
    public static AbstractModel getModel(String modelPackage) {
        try {
            String className = "com.matheus.erp." + modelPackage;

            Class<?> modelClass = Class.forName(className);
            AbstractModel model = (AbstractModel) modelClass.newInstance();

            // Store the model instance in the map
            singletonModels.put(modelPackage, model);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves a singleton instance of AbstractModel based on the provided modelPackage.
     *
     * @param modelPackage The package name of the model class. e.: 'product.ProductModel'
     * @return A singleton instance of AbstractModel for the specified modelPackage.
     */
    public static AbstractModel getSingleton(String modelPackage) {
        AbstractModel singleton = singletonModels.get(modelPackage);

        // If the singleton doesn't exist, create and store a new singleton instance
        if (singleton == null) {
            singleton = getModel(modelPackage);
            singletonModels.put(modelPackage, singleton);
        }

        return singleton;
    }
}
