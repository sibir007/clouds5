package io.github.sibir007.clouds5.core.transactions;

public enum SQL_STOCK {
    CREATE_TABLE_base_transaction("CREATE TABLE base_transaction (" +
            "id	TEXT NOT NULL UNIQUE," +
            "root_id	TEXT NOT NULL," +
            "parent_id	TEXT NOT NULL," +
            "complexity	INTEGER NOT NULL," +
            "direction	INTEGER NOT NULL," +
            "status	INTEGER NOT NULL," +
            "type	INTEGER NOT NULL," +
            "category	INTEGER NOT NULL," +
            "response_id	TEXT NOT NULL," +
            "FOREIGN KEY(direction) REFERENCES transaction_direction(id)," +
            "PRIMARY KEY(id)," +
            "FOREIGN KEY(status) REFERENCES taransaction_status(status_id)," +
            "FOREIGN KEY(type) REFERENCES transaction_type(type_id)," +
            "FOREIGN KEY(category) REFERENCES transaction_category(category_id)," +
            "FOREIGN KEY(complexity) REFERENCES transaction_complexity(complexity_id)" +
            ");"),
    INSERT_INTO_base_transaction("INSERT INTO base_transaction (" +
            "id," + //String 1
            "root_id," + //String 2
            "parent_id," + //String 3
            "complexity, " + //int 4
            "direction, " + //int 5
            "status, " + //int 6
            "type, " + //int 7
            "category, " + //int 8
            "response_id) " + //String 3
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"),
    INSERT_INTO_abstract_simple_transaction("INSERT INTO abstract_simple_transaction (" +
            "id, " +
            "host, " +
            "port) " +
            "VALUES (?, ?, ?);"),
    INSERT_INTO_add_cloud_transaction("INSERT INTO add_cloud_transaction (id) VALUES (?)"),
    SELECT_ALL_FROM_TABLE_WHERE_ID("SELECT * FROM ? WHERE ? = ?;"),
    GET_TRANSACTION_TYPE("SELECT" +
            "                 transaction_type.type" +
            "              FROM" +
            "                 base_transaction " +
            "             INNER JOIN" +
            "                 transaction_type" +
            "              ON" +
            "                 transaction_type.type_id = base_transaction.type" +
            "             WHERE" +
            "                 base_transaction.id = ?;");
    private final String sql;

    private SQL_STOCK(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
