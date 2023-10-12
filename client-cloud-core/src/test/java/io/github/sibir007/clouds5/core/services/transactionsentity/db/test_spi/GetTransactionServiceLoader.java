package io.github.sibir007.clouds5.core.services.transactionsentity.db.test_spi;

import io.github.sibir007.clouds5.core.transactions.Transaction.TransactionType;

import java.util.HashMap;
import java.util.Map;

public class GetTransactionServiceLoader {
    private static Map<TransactionType, TestGetTransactionForIdService> serviceMap = new HashMap<>();

    static {
    }

    public static synchronized TestGetTransactionForIdService getTransactionForIdService(TransactionType transactionType) {
        return null;
    }
}
