package io.github.sibir007.clouds5.core.services.transactionsentity.db;

import io.github.sibir007.clouds5.core.properties.TransactionEntityDBProperty;
import io.github.sibir007.clouds5.core.properties.TransactionEntitySqliteDBProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteTransactionEntityServiceDBConnectionProvider extends AbstractTransactionEntityServiceDBConnectionProvider {
    private static Logger logger = LogManager.getLogger();

    private static TransactionEntityServiceDBConnectionProvider connectionProvider ;

    protected String database_name;

    public static TransactionEntityServiceDBConnectionProvider getSingleton() {
        if (connectionProvider == null) {
            connectionProvider = new SqliteTransactionEntityServiceDBConnectionProvider(TransactionEntityDBProperty.getSingletonInstance(), TransactionEntitySqliteDBProperty.getSingletonInstance());
        }
        return connectionProvider;
    }

    protected SqliteTransactionEntityServiceDBConnectionProvider(TransactionEntityDBProperty dbProperty, TransactionEntitySqliteDBProperty dbSqliteProperty) {
        super();
        this.database_name = dbProperty.getDatabaseName();
    }

    ;

    protected Connection getConnectionSpecific(String url) {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            logger.error("SQLException");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void performSpecificDBImplementationAction(Connection connection) {

    }

    @Override
    protected String getUrl() {
        return "jdbc:" + "sqlite" + ":" + database_name + ".db";
    }
}
