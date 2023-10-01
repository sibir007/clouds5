package io.github.sibir007.clouds5.core.services.transactionsentity.db;

import io.github.sibir007.clouds5.core.BaseTestCase;
import io.github.sibir007.clouds5.core.properties.TransactionEntityDBProperty;
import io.github.sibir007.clouds5.core.properties.TransactionEntitySpProperty;
import io.github.sibir007.clouds5.core.properties.TransactionEntitySqliteDBProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// TODO: 02.10.2023 остановился здесь. Делать sql для инициализации bd
class DatabaseWrapperTransactionsEntityServiceImplTest  extends BaseTestCase {
    private DatabaseWrapperTransactionsEntityServiceImpl entityService;
    private TransactionEntityServiceDBConnectionProvider connectionProvider;

    @BeforeEach
    void setUp() {
        entityService = new DatabaseWrapperTransactionsEntityServiceImpl();
        TransactionEntityDBProperty dbProp = mock(TransactionEntityDBProperty.class);
        TransactionEntitySqliteDBProperty sqliteDbProp = mock(TransactionEntitySqliteDBProperty.class);
        when(sqliteDbProp.getDbms()).thenReturn("db/test/sqlite_test.db");
        connectionProvider = new SqliteTransactionEntityServiceDBConnectionProvider(dbProp, sqliteDbProp);
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("EntityServiсе should ...")
    @Test
    void entityServiceShould(){

    }
}