package io.github.sibir007.clouds5.core.services.transactionsentity.db;

import io.github.sibir007.clouds5.core.Cloud;
import io.github.sibir007.clouds5.core.services.spi.TransactionEntityService;
import io.github.sibir007.clouds5.core.properties.TransactionEntityDBProperty;
import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import io.github.sibir007.clouds5.core.transactions.DbTablesDirectory;
import io.github.sibir007.clouds5.core.transactions.SQL_STOCK;
import io.github.sibir007.clouds5.core.transactions.Transaction;
import io.github.sibir007.clouds5.core.transactions.response.TransactionResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

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
                logger.trace("createAddCloudTransaction(Cloud cloud) in try block");
                connection.setAutoCommit(false);
                prepareINSERT_INTO_base_transactionStatement(preparedINSERT_INTO_base_transactionStatement, transaction);
                preparedINSERT_INTO_base_transactionStatement.execute();
                prepareINSERT_INTO_abstract_simple_transactionStatement(preparedINSERT_INTO_abstract_simple_transactionStatement, transaction);
                preparedINSERT_INTO_abstract_simple_transactionStatement.execute();
                preparedINSERT_INTO_add_cloud_transactionStatement.setString(1, transaction.getId());
                preparedINSERT_INTO_add_cloud_transactionStatement.execute();
                connection.commit();
                logger.trace("createAddCloudTransaction(Cloud cloud) out try block");

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
    public Transaction getTransaction(String transactionId) throws Exception {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedSELECT_ALL_FROM_WHEREStatement = connection.prepareStatement(SQL_STOCK.SELECT_ALL_FROM_TABLE_WHERE_ID.getSql());
        ) {
            try {
                logger.trace("getTransaction(String transactionId) in try block");

                prepareSELECT_ALL_FROM_WHERE_Statement(preparedSELECT_ALL_FROM_WHEREStatement,
                        DbTablesDirectory.BASE_TRANSACTION,
                        "id",
                        transactionId);
                ResultSet resultSet = preparedSELECT_ALL_FROM_WHEREStatement.executeQuery();
                logger.trace("getTransaction(String transactionId) out try block");

            } catch (SQLException e) {
                logger.trace("exception");
                connection.rollback();
                throw e;
            }
        }
        return null;
    }

    public Transaction.TransactionType getTransactionType(String transactionId) throws Exception {
        Transaction.TransactionType type;
        logger.trace("getTransactionType (String transactionId) in");

        try (Connection connection = connectionProvider.getConnection();
//             Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_STOCK.GET_TRANSACTION_TYPE.getSql());
             ){
            try {
                logger.trace("getTransactionType (String transactionId) in try block");
                preparedStatement.setString(1, transactionId);
                ResultSet resultSet = preparedStatement.executeQuery();
//                ResultSet resultSet = statement.executeQuery("SELECT                 transaction_type.type              FROM                 base_transaction              INNER JOIN                 transaction_type              ON                 transaction_type.type_id = base_transaction.type             WHERE                 base_transaction.id = 1696696229426");
                logger.trace("getTransactionType (String transactionId) out try block");
                type = Transaction.TransactionType.valueOf(resultSet.getString("type"));
                logger.trace("getTransactionType (String transactionId) out try block");
            } catch (SQLException e) {
                logger.trace("exception");
                throw e;
            }
        }
        return type;
    }

    private void prepareSELECT_ALL_FROM_WHERE_Statement(PreparedStatement statement, String tableName, String column, String criterion) throws SQLException {
        statement.setString(1, tableName);
        statement.setString(2, column);
        statement.setString(3, criterion);
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
