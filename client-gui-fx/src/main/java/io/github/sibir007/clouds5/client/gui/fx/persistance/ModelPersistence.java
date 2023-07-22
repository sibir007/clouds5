package io.github.sibir007.clouds5.client.gui.fx.persistance;

import io.github.sibir007.clouds5.client.core.Cloud;

import java.util.ArrayList;
import java.util.List;

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
