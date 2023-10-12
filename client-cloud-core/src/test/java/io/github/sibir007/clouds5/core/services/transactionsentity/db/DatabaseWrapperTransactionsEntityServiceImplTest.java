package io.github.sibir007.clouds5.core.services.transactionsentity.db;

import io.github.sibir007.clouds5.core.BaseTestCase;
import io.github.sibir007.clouds5.core.Cloud;
import io.github.sibir007.clouds5.core.CloudImpl;
import io.github.sibir007.clouds5.core.properties.TransactionEntityDBProperty;
import io.github.sibir007.clouds5.core.transactions.AddCloudTransaction;
import io.github.sibir007.clouds5.core.transactions.Transaction;
import io.github.sibir007.clouds5.core.transactions.Transaction.TransactionType;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

// TODO: 03.10.2023 остановился здесь. Делать sql для инициализации bd
class DatabaseWrapperTransactionsEntityServiceImplTest extends BaseTestCase {
    private DatabaseWrapperTransactionsEntityServiceImpl entityService;
    private TransactionEntityServiceDBConnectionProvider connectionProvider;

    @BeforeEach
    void setUp() {
        entityService = new DatabaseWrapperTransactionsEntityServiceImpl();
        assertEquals("sqlite", TransactionEntityDBProperty.getSingletonInstance().getDbms(), () -> "db should be sqlite");

    }

    @AfterEach
    void tearDown() {
    }
@Disabled("")
    @DisplayName("EntityServiсе should ...")
    @Test
    void entityServiceShould(){
    Cloud cloud = new CloudImpl("aaaaaaaaaa", 55);
    try {
        AddCloudTransaction transaction = entityService.createAddCloudTransaction(cloud);
        assertEquals(transaction.getHost(), cloud.getHost(), () ->  "hosts should be equals");
        assertEquals(transaction.getPort(), cloud.getPort(), () -> "ports should be equals");
        Optional<TransactionType> transactionType = entityService.getTransactionType(transaction.getId());
//
        assertEquals(TransactionType.ADD_CLOUD, transactionType.get(), () -> "transaction type should be ADD_CLOUD");
        assertEquals(transaction.getId(), entityService.getTransaction(transaction.getId()).get().getId());
        Optional<Transaction> transaction_from_db = entityService.getTransaction(transaction.getId());
        assertInstanceOf(AddCloudTransaction.class, transaction_from_db.get(), ()-> "transaction should be AddCloudTransaction");
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
}