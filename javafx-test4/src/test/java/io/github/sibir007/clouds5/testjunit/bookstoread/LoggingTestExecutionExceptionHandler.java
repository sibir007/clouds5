package io.github.sibir007.clouds5.testjunit.bookstoread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class LoggingTestExecutionExceptionHandler implements TestExecutionExceptionHandler {
    Logger logger = LogManager.getLogger();
    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        logger.trace("exception thrown " + throwable);
        throw throwable;
    }
}
