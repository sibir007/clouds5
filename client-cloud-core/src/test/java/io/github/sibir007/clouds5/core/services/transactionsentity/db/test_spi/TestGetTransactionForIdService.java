package io.github.sibir007.clouds5.core.services.transactionsentity.db.test_spi;

import io.github.sibir007.clouds5.core.transactions.Transaction;

public interface TestGetTransactionForIdService {
    Transaction getTransaction(String id);
}
