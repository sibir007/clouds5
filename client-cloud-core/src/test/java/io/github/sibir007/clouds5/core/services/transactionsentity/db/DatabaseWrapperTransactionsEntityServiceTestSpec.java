package io.github.sibir007.clouds5.core.services.transactionsentity.db;

import io.github.sibir007.clouds5.core.BaseTestCase;
import io.github.sibir007.clouds5.core.Cloud;
import io.github.sibir007.clouds5.core.CloudImpl;
import io.github.sibir007.clouds5.core.services.transactionsentity.db.DatabaseWrapperTransactionsEntityServiceImpl;
import io.github.sibir007.clouds5.core.services.transactionsentity.db.SqliteTransactionEntityServiceDBConnectionProvider;
import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("DatabaseWrapperTransactionsEntityServiceImpl testing together SqliteTransactionEntityServiceDBConnectionProvider")
class DatabaseWrapperTransactionsEntityServiceTestSpec extends BaseTestCase {
    @Spy
    SqliteTransactionEntityServiceDBConnectionProvider connectionProvider;
    private DatabaseWrapperTransactionsEntityServiceImpl transactionsEntityService;
    @BeforeEach
    void init(){
        transactionsEntityService = new DatabaseWrapperTransactionsEntityServiceImpl();
    }
    @Test
    @DisplayName("createAddCloudTransaction(Cloud cloud) should return AddCloudTransaction")
    void createAddCloudTransactionTest(){
        Cloud cloud = new CloudImpl("10.10.10.10", 4);
        AddCloudTransaction transaction = null;
        try {
            transaction = transactionsEntityService.createAddCloudTransaction(cloud);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertTrue(transaction instanceof AddCloudTransaction);
    }
//    @Test
//    @DisplayName("getTransaction(String id) should return AddCloudTransaction")
//    void createAddCloudTransactionTest(){
//        Cloud cloud = new CloudImpl("10.10.10.10", 4);
//        AddCloudTransaction transaction = transactionsEntityService.createAddCloudTransaction(cloud);
//        assertTrue(transaction instanceof AddCloudTransaction);
//    }

}
