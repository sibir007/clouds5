package io.github.sibir007.clouds5.client.gui.fx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class ModelImpl {
    private static Logger logger = LogManager.getLogger();

    private static final String fname = "MODEL";
    private static ModelImpl model;
    private ObservableList<CloudBeenImpl> clouds;
    private ModelImpl(){
        logger.trace("in constructor");
        File f = new File(fname);
        clouds = FXCollections.observableArrayList();
        clouds.addAll(new CloudBeenImpl("djflsjflsf", 67), new CloudBeenImpl("ttttttttttt", 99));
        logger.trace("clouds added");

        if (!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                logger.trace("io exeption");
                throw new RuntimeException("file cteat exeption!!!!!!!!!!!");
            }
            clouds = FXCollections.observableArrayList();
            clouds.addAll(new CloudBeenImpl("djflsjflsf", 67), new CloudBeenImpl("ttttttttttt", 99));
            logger.trace("clouds added");
        }
        else {
            try (FileInputStream fis = new FileInputStream(fname);
                 ObjectInputStream ois = new ObjectInputStream(fis)){
                clouds = (ObservableList<CloudBeenImpl>) ois.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    public static ModelImpl getModel(){
        if (model == null){
            model = new ModelImpl();
        }
        return model;
    }

    public ObservableList<CloudBeenImpl> getClouds(){
        return clouds;
    }
    public void save(){
//        logger.trace("in save");
//        if (clouds != null) {
//            try (OutputStream os = new FileOutputStream(fname);
//                 ObjectOutput oos = new ObjectOutputStream(os)){
//                oos.writeObject(clouds);
//                logger.trace("cloud save");
//            } catch (Exception e) {
//                throw new RuntimeException("file write exception");
//            }
//        }

    }


}
