package io.github.sibir007.clouds5.test.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class TestPropertiesApp {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        String rootPath  = Objects.requireNonNull(Thread.
                        currentThread().
                        getContextClassLoader().
                        getResource("")).
                getPath();
        logger.trace(rootPath);
    }
}
