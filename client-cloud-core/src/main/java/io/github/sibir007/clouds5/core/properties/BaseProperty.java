package io.github.sibir007.clouds5.core.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public abstract class BaseProperty {
    private static String propertyFilesDir = "properties/client-cloud-core/";
    public static String getPropertyFilesDir(){
        return propertyFilesDir;
    }

    public static void setPropertyFilesDir(String newPropertyFilesDir){
        propertyFilesDir = newPropertyFilesDir;
    }
    private static Logger logger = LogManager.getLogger();

    protected Properties prop;

    protected BaseProperty(String propertyFileName){
        prop = new Properties();
        String propertyFilePath = propertyFilesDir + propertyFileName;
        logger.info("root dir" + Thread.currentThread().getContextClassLoader().getResource(propertyFilePath).getPath());


        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFilePath);){
            prop.loadFromXML(is);

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
