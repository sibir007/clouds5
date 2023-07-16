package io.github.sibir007.clouds5.client.gui.fx.model;

import io.github.sibir007.clouds5.client.core.Cloud;
import javafx.collections.ObservableSet;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Model {
    private static final String FILE = "MODEL";
    private static Model model;
    private Set<CloudImpl> clouds;
    private Model(){
        File f = new File(FILE);
        if (!f.exists()){
            clouds = new HashSet<>();
        }
        else {
            try (FileInputStream fis = new FileInputStream(f);
                 ObjectInputStream ois = new ObjectInputStream(fis)){
                clouds = (Set<CloudImpl>) ois.readObject();
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

    public Set<CloudImpl> getClouds(){
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
