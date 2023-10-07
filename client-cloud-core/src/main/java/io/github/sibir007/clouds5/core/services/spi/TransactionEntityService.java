package io.github.sibir007.clouds5.core.services.spi;

import io.github.sibir007.clouds5.core.Cloud;
import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import io.github.sibir007.clouds5.core.transactions.Transaction;
import io.github.sibir007.clouds5.core.transactions.response.TransactionResponse;

public interface TransactionEntityService {


    AddCloudTransaction createAddCloudTransaction(Cloud cloud) throws Exception;
    Transaction getTransaction(String transactionId);


    void saveTransactionResponse(TransactionResponse transactionResponse) throws Exception;
    TransactionResponse getTransactionResponse();
}
