package io.github.sibir007.clouds5.client.gui.fx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Model {
    private static Logger logger = LogManager.getLogger();

    private static final String FILE = "MODEL";
    private static Model model;
    private ObservableList<CloudImpl> clouds;
    private Model(){
        logger.trace("in constructor");
        File f = new File(FILE);
        if (!f.exists()){
            clouds = FXCollections.observableArrayList();
            clouds.addAll(new CloudImpl("djflsjflsf", 67), new CloudImpl("ttttttttttt", 99));
            logger.trace("clouds added");
        }
        else {
            try (FileInputStream fis = new FileInputStream(f);
                 ObjectInputStream ois = new ObjectInputStream(fis)){
                clouds = (ObservableList<CloudImpl>) ois.readObject();
            } catch (Exception e) {
                throw new RuntimeException("file read exception");
            }
        }
    }
    public static Model getModel(){
        if (model == null){
            model = new Model();
        }
        return model;
    }

    public ObservableList<CloudImpl> getClouds(){
        return clouds;
    }
    public void save(){
        if (clouds != null) {
            try (OutputStream os = new FileOutputStream(FILE);
                 ObjectOutput oos = new ObjectOutputStream(os)){
                oos.writeObject(clouds);
            } catch (Exception e) {
                throw new RuntimeException("file write exception");
            }
        }

    }


}
