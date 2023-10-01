package io.github.sibir007.clouds5.core.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseProperty {
    private static Logger logger = LogManager.getLogger();

    protected Properties prop;

    protected BaseProperty(String filePropertyPath){
        prop = new Properties();
        logger.info("root dir" + new File(".").getAbsolutePath());

        try (FileInputStream fis = new FileInputStream(filePropertyPath);){
            prop.loadFromXML(fis);

        } catch (FileNotFoundException e) {
            logger.error("not file found" + new File(".").getAbsolutePath());
            throw new RuntimeException(e);
        } catch (IOException ex) {
            logger.error("io exception in close file");
            throw new RuntimeException(ex);
        }

    }
    public String getPropertyForName(String propertyName) {
        return prop.getProperty(propertyName);
    }

}
