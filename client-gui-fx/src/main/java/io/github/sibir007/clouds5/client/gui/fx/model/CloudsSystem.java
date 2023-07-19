package io.github.sibir007.clouds5.client.gui.fx.model;

import io.github.sibir007.clouds5.client.core.Cloud;
import io.github.sibir007.clouds5.client.gui.fx.persistance.ModelPersistence;
import io.github.sibir007.clouds5.client.gui.fx.util.BeenModelConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CloudsSystem {
    private static CloudsSystem cloudsSystem;
    private ObservableList<Cloud> clouds;
    private ModelPersistence modelPersistence;

    public static CloudsSystem get(){
        if (cloudsSystem == null){
            cloudsSystem = new CloudsSystem();
        }
        return cloudsSystem;
    }
    private CloudsSystem() {
        modelPersistence = ModelPersistence.getFilePersistenceProvider();
        clouds = BeenModelConverter.convertToBeen(modelPersistence.getModel());
    }
}
