package io.github.sibir007.clouds5.client.gui.fx.model;

import io.github.sibir007.clouds5.client.core.Cloud;
import io.github.sibir007.clouds5.client.gui.fx.persistance.ModelPersistence;
import io.github.sibir007.clouds5.client.gui.fx.util.BeenModelConverter;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Model {
    private static Logger logger = LogManager.getLogger();

    private static Model model;
    private ObservableList<CloudBeenImpl> clouds;
    private Model() {
        logger.trace("in constructor");
        clouds = BeenModelConverter.convertToBeen(ModelPersistence.getModelFromFile());
        logger.trace("clouds added");

    }
    public static Model getModel(){
        if (model == null){
            model = new Model();
        }
        return model;
    }

    public ObservableList<CloudBeenImpl> getClouds(){
        return clouds;
    }
    public void save(){
        logger.trace("in save");
        if (clouds != null) {
            ModelPersistence.sameModelToFile(BeenModelConverter.convertFromBeen(clouds));
        }

    }

    public boolean addCloud(CloudBeenImpl cloud){
        return clouds.add(cloud);
    }
    public CloudBeenImpl removeCloud(int cloudIndex){
       return clouds.remove(cloudIndex);
    }

    public boolean removeCloud(Cloud cloud){
        return clouds.remove(cloud);
    }

    public CloudBeenImpl getCloud(int cloudIndex){
        return clouds.get(cloudIndex);
    }




}
