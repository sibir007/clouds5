package io.github.sibir007.clouds5.core.services.transactionsentity.db;

import io.github.sibir007.clouds5.core.Cloud;
import io.github.sibir007.clouds5.core.services.spi.TransactionEntityService;
import io.github.sibir007.clouds5.core.properties.TransactionEntityDBProperty;
import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import io.github.sibir007.clouds5.core.transactions.SQL_STOCK;
import io.github.sibir007.clouds5.core.transactions.Transaction;
import io.github.sibir007.clouds5.core.transactions.response.TransactionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseWrapperTransactionsEntityServiceImpl implements TransactionEntityService {
    private static Logger logger = LogManager.getLogger();
    private final static String INDEFINED_SQL_PARAMETR = "indefined";
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


    // TODO: 05.10.2023 остановился здесь, доделывать создание  AddCloudTransaction
    @Override
    public AddCloudTransaction createAddCloudTransaction(Cloud cloud) throws Exception {
        logger.trace("in createAddCloudTransaction(Cloud cloud)");
        String id = getTransactionId();
        AddCloudTransaction transaction = new AddCloudTransaction(
                id,
                id,
                id,
                Transaction.Direction.IN,
                Transaction.Status.NEW,
                INDEFINED_SQL_PARAMETR,
                cloud.getHost(),
                cloud.getPort());
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedINSERT_INTO_base_transactionStatement = connection.prepareStatement(SQL_STOCK.INSERT_INTO_base_transaction.getSql());
             PreparedStatement preparedINSERT_INTO_abstract_simple_transactionStatement = connection.prepareStatement(SQL_STOCK.INSERT_INTO_abstract_simple_transaction.getSql());
             PreparedStatement preparedINSERT_INTO_add_cloud_transactionStatement = connection.prepareStatement(SQL_STOCK.INSERT_INTO_add_cloud_transaction.getSql());
             ) {
            try {
                logger.trace("createAddCloudTransaction(Cloud cloud) try block");
                connection.setAutoCommit(false);
                prepareINSERT_INTO_base_transactionStatement(preparedINSERT_INTO_base_transactionStatement, transaction);
                preparedINSERT_INTO_base_transactionStatement.execute();
                prepareINSERT_INTO_abstract_simple_transactionStatement(preparedINSERT_INTO_abstract_simple_transactionStatement, transaction);
                preparedINSERT_INTO_abstract_simple_transactionStatement.execute();
                preparedINSERT_INTO_add_cloud_transactionStatement.setString(1, transaction.getId());
                preparedINSERT_INTO_add_cloud_transactionStatement.execute();
                connection.commit();
            } catch (SQLException e) {
                logger.trace("exception");
                connection.rollback();
                throw e;
            }
        }
        return transaction;
    }

    private void prepareINSERT_INTO_abstract_simple_transactionStatement(PreparedStatement preparedINSERT_INTO_abstract_simple_transactionStatement, AddCloudTransaction transaction) throws SQLException {
        preparedINSERT_INTO_abstract_simple_transactionStatement.setString(1, transaction.getId());
        preparedINSERT_INTO_abstract_simple_transactionStatement.setString(2, transaction.getHost());
        preparedINSERT_INTO_abstract_simple_transactionStatement.setInt(3, transaction.getPort());
    }

    private void prepareINSERT_INTO_base_transactionStatement(PreparedStatement preparedStatement, AddCloudTransaction transaction) throws SQLException {
        preparedStatement.setString(1, transaction.getId());
        preparedStatement.setString(2, transaction.getRootId());
        preparedStatement.setString(3, transaction.getParentId());
        preparedStatement.setInt(4, transaction.getComplexity().getDbId());
        preparedStatement.setInt(5, transaction.getDirection().getDbId());
        preparedStatement.setInt(6, transaction.getStatus().getDbId());
        preparedStatement.setInt(7, transaction.getType().getDbId());
        preparedStatement.setInt(8, transaction.getCategory().getDbId());
        preparedStatement.setString(9, transaction.getTransactionResponseId());
    }

    private String getTransactionId() {
        return String.valueOf(System.currentTimeMillis());
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
        // SQL_STOCK statement for creating a new table
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
