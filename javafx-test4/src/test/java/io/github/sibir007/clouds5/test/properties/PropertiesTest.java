package io.github.sibir007.clouds5.test.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;

public class PropertiesTest {
    private static Logger logger = LogManager.getLogger();
    @Test
    void testProperties() throws IOException {
        String rootPath = Thread.currentThread().
                getContextClassLoader().
                getResource("").
                getPath();
        logger.trace(rootPath);
        String appConfFile = rootPath + "app.properties";
        String catalogConfigPath = rootPath + "catalog.properties";
        Properties appProperties = new Properties();
        appProperties.load(new FileInputStream(appConfFile));
        assertEquals("1.0", appProperties.getProperty("version"));
        assertEquals("TestApp", appProperties.getProperty("name"));


        Properties catalogProperties = new Properties();
        catalogProperties.load(new FileInputStream(catalogConfigPath));
    }
}
