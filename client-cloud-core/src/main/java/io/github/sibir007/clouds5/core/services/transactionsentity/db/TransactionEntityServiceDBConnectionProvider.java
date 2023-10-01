package io.github.sibir007.clouds5.core.services.transactionsentity.db;

import java.sql.Connection;

public interface TransactionEntityServiceDBConnectionProvider {

    Connection getConnection();
//    TransactionEntityServiceDBConnectionProvider get
}
