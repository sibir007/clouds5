package io.github.sibir007.clouds5.client.gui.fx.persistance;

import io.github.sibir007.clouds5.client.gui.fx.model.CloudImpl;

import java.util.ArrayList;

public interface ModelPersistence {
    ArrayList<CloudImpl> getModel();
    void saveModel(ArrayList<CloudImpl> model);
    static ArrayList<CloudImpl> getModelFromFile(){
        return FilePersistenceProvider.PROVIDER.getModel();
    }

    static void sameModelToFile(ArrayList<CloudImpl> clouds){
        FilePersistenceProvider.PROVIDER.saveModel(clouds);
    }

    static ModelPersistence getFilePersistenceProvider(){
        return FilePersistenceProvider.PROVIDER;
    }
}
