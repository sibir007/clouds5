package io.github.sibir007.clouds5.core.services.transactionsentity;

import io.github.sibir007.clouds5.core.Cloud;
import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import io.github.sibir007.clouds5.core.services.spi.TransactionEntityService;
import io.github.sibir007.clouds5.core.transactions.Transaction;
import io.github.sibir007.clouds5.core.transactions.response.TransactionResponse;

// TODO: 01.10.2023 делать реализацию.
//  а может нет...
public class DataAbstractionTransactionsEntityServiceImpl implements TransactionEntityService {
//    @Override
    public AddCloudTransaction createAddCloudTransaction(Cloud cloud) {
        return null;
    }

    @Override
    public Transaction getTransaction(String transactionId) {
        return null;
    }

    //    @Override
    public void saveTransactionResponse(TransactionResponse transactionResponse) {

    }

    @Override
    public TransactionResponse getTransactionResponse() {
        return null;
    }
}
