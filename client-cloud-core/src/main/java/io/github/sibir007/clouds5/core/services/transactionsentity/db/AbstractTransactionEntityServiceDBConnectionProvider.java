package io.github.sibir007.clouds5.core.services.transactionsentity.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public abstract class AbstractTransactionEntityServiceDBConnectionProvider implements TransactionEntityServiceDBConnectionProvider {
    private static Logger logger = LogManager.getLogger();



    protected AbstractTransactionEntityServiceDBConnectionProvider() {

    }

    public Connection getConnection() {
        String url = getUrl();
        Connection connection = getConnectionSpecific(url);
        logger.info("Connection to SQLite has been established");
        performSpecificDBImplementationAction(connection);
        return connection;
    }

    protected abstract Connection getConnectionSpecific(String url);

    protected abstract void performSpecificDBImplementationAction(Connection connection);

    protected abstract String getUrl();

}
