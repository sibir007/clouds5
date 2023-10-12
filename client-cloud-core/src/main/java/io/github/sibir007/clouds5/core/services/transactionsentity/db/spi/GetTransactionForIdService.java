package io.github.sibir007.clouds5.core.services.transactionsentity.db.spi;

import io.github.sibir007.clouds5.core.transactions.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface GetTransactionForIdService {
    void setConnection(Connection connection);
    Optional<Transaction> getTransaction(String id) throws SQLException;
}
