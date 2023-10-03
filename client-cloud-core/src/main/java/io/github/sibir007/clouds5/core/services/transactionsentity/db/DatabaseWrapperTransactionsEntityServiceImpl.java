package io.github.sibir007.clouds5.core.services.transactionsentity.db;

import io.github.sibir007.clouds5.core.Cloud;
import io.github.sibir007.clouds5.core.services.spi.TransactionEntityService;
import io.github.sibir007.clouds5.core.properties.TransactionEntityDBProperty;
import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import io.github.sibir007.clouds5.core.transactions.Transaction;
import io.github.sibir007.clouds5.core.transactions.response.TransactionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseWrapperTransactionsEntityServiceImpl implements TransactionEntityService {
    private static Logger logger = LogManager.getLogger();
    private TransactionEntityServiceDBConnectionProvider connectionProvider;

    private static TransactionEntityService transactionEntityService;

    public static TransactionEntityService getTransactionEntityServiceSingleton() {
        if (transactionEntityService == null) {
            transactionEntityService = new DatabaseWrapperTransactionsEntityServiceImpl();
        }
        return transactionEntityService;
    }

    protected DatabaseWrapperTransactionsEntityServiceImpl() {
        String dbms = TransactionEntityDBProperty.getSingletonInstance().getDbms();
        if (dbms.equals("sqlite")) {

            logger.info("sqlite selected");
            connectionProvider = SqliteTransactionEntityServiceDBConnectionProvider.getSingleton();
        } else if (dbms.equals("mysql")) {
            logger.info("mysql selected");
            connectionProvider = MysqlTransactionEntityServiceDBConnectionProvider.getSingleton();
        } else {
            throw new RuntimeException("not supported database management system");
        }
    }



    protected void setTransactionEntityServiceDBConnectionProvider(TransactionEntityServiceDBConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }


    @Override
    public AddCloudTransaction createAddCloudTransaction(Cloud cloud) {
        return new AddCloudTransaction("34242424",
                "sdlkfjsl",
                "dkfjdljfdlf",
                Transaction.Direction.IN,
                Transaction.Status.NEW,
                "dlkfjlsdfj",
                59,
                "dsjflsjfls");
    }

    @Override
    public Transaction getTransaction(String transactionId) {
        return null;
    }

    @Override
    public void saveTransactionResponse(TransactionResponse transactionResponse) {

    }

    @Override
    public TransactionResponse getTransactionResponse() {
        return null;
    }

    //fore testing only
    protected void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";

        try (Connection conn = connectionProvider.getConnection();
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            logger.info("table created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
