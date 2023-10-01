package io.github.sibir007.clouds5.testjunit.bookstoread.extension;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.reflect.Field;

public class LoggingExtension implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object testInstance,
                                        ExtensionContext context)
            throws Exception {
        Logger logger = LogManager.getLogger();
        Field field = testInstance.getClass().getDeclaredField("logger");
        field.set(testInstance, logger);
    }
}
