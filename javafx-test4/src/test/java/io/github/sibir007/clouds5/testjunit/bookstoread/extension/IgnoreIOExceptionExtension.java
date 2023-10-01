package io.github.sibir007.clouds5.testjunit.bookstoread.extension;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.io.IOException;

public class IgnoreIOExceptionExtension implements TestExecutionExceptionHandler {
    private Logger logger = LogManager.getLogger();
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (throwable instanceof IOException){
            logger.error("IO Exception {}", throwable);
            return;
        }
        throw throwable;
    }
}
