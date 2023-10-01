package io.github.sibir007.clouds5.client.gui.fx.persistance;

import io.github.sibir007.clouds5.core.CloudImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

public class FilePersistenceProvider implements ModelPersistence {
    private static Logger logger = LogManager.getLogger();
    private static final String FILENAME = "ModelPersistenceFile";
    private static final File FILE = new File(FILENAME);

    public static final ModelPersistence PROVIDER = new FilePersistenceProvider();

    private FilePersistenceProvider() {
    }

    @Override
    public ArrayList<CloudImpl> getModel() {
        if (FILE.exists()) {
            try (FileInputStream fis = new FileInputStream(FILENAME);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                ArrayList<CloudImpl> clouds = (ArrayList<CloudImpl>) ois.readObject();
                return clouds;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return new ArrayList<CloudImpl>();
    }

    @Override
    public void saveModel(ArrayList<CloudImpl> model) {
        if (!FILE.exists()) {
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (FileOutputStream fos = new FileOutputStream(FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(model);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
