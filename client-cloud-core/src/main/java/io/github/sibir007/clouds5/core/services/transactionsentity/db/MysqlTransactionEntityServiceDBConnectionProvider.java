package io.github.sibir007.clouds5.core.services.transactionsentity.db;

import io.github.sibir007.clouds5.core.properties.TransactionEntityDBProperty;
import io.github.sibir007.clouds5.core.properties.TransactionEntityMySqlDBProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlTransactionEntityServiceDBConnectionProvider extends AbstractTransactionEntityServiceDBConnectionProvider {
    private static Logger logger = LogManager.getLogger();

    private static TransactionEntityServiceDBConnectionProvider connectionProvider;

    private final String database_name;
    private final String user_name;
    private final String password;
    private final String server_name;
    private final String port_number;

    public static TransactionEntityServiceDBConnectionProvider getSingleton() {
        if (connectionProvider == null) {
            connectionProvider = new MysqlTransactionEntityServiceDBConnectionProvider(TransactionEntityDBProperty.getSingletonInstance(), TransactionEntityMySqlDBProperty.getSingletonInstance());
        }
        return connectionProvider;
    }

    protected MysqlTransactionEntityServiceDBConnectionProvider(TransactionEntityDBProperty dbProperty, TransactionEntityMySqlDBProperty dbMsqlProperty) {
        super();
        this.database_name = dbProperty.getDatabaseName();
        this.user_name = dbMsqlProperty.getUserName();
        this.password = dbMsqlProperty.getPassword();
        this.server_name =dbMsqlProperty.getServerName();
        this.port_number = dbMsqlProperty.getPortNumber();
    }


    @Override
    protected Connection getConnectionSpecific(String url) {
        try {
            return DriverManager.getConnection(url, user_name, password);
        } catch (SQLException e) {
            logger.error("SQLException");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void performSpecificDBImplementationAction(Connection connection) {
        try {
            connection.setCatalog(database_name);
        } catch (SQLException e) {
            logger.error("SQLException");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String getUrl() {
        return "jdbc:" + "mysql" + "://" + server_name + ":" + port_number + "/";
    }
}
