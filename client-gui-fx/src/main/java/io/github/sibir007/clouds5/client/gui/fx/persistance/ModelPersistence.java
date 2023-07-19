package io.github.sibir007.clouds5.client.gui.fx.persistance;

import io.github.sibir007.clouds5.client.core.Cloud;

import java.util.List;

public interface ModelPersistence {
    List<Cloud> getModel();
    void saveModel(List<Cloud> model);
    static List<Cloud> getModelFromFile(){
        return FilePersistenceProvider.PROVIDER.getModel();
    }

    static void sameModelToFile(List<Cloud> clouds){
        FilePersistenceProvider.PROVIDER.saveModel(clouds);
    }

    static ModelPersistence getFilePersistenceProvider(){
        return FilePersistenceProvider.PROVIDER;
    }
}
