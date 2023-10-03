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

    @DisplayName("EntityServiсе should ...")
    @Test
    void entityServiceShould() {
        entityService.createNewTable();
    }
}