package io.github.sibir007.clouds5.core.services.transactionsentity.db.spi;

import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import io.github.sibir007.clouds5.core.transactions.SQL_STOCK;
import io.github.sibir007.clouds5.core.transactions.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static io.github.sibir007.clouds5.core.transactions.Transaction.TransactionType.ADD_CLOUD;

@TransactionTypeAnnotation(ADD_CLOUD)
public class GetAddCloudTransactionForIdSP implements GetTransactionForIdService {
    private Connection connection;

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Transaction> getTransaction(String id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_STOCK.GET_ADD_CLOUD_TRANSACTION_WHERE_ID.getSql());){
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            String transactionId   = resultSet.getString("id");
            if (transactionId == null){
                return Optional.empty();
            }
            Transaction.Direction direction = Transaction.Direction.valueOf(resultSet.getString("direction"));
            Transaction.Status status = Transaction.Status.valueOf(resultSet.getString("status"));
            String responseId = resultSet.getString("response_Id");
            String host = resultSet.getString("host");
            int port = resultSet.getInt("port");
            return Optional.of(new AddCloudTransaction(transactionId, direction, status, responseId, host, port));
        }

    }
}
