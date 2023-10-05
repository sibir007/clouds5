package io.github.sibir007.clouds5.core.transactions;

public abstract class AbstractAutorisedTransaction extends AbstractSimpleTransaction implements AutorisedTransaction {
    protected final static String NOUSED_NAME = "UNKNOWN";
    protected final static String NOUSED_PASSWORD = "UNKNOWN";
private final String userName;
private final String password;


    protected AbstractAutorisedTransaction(String id,
                                           String rootId,
                                           String parentId,
                                           Direction direction,
                                           Status status,
                                           TransactionType type,
                                           TransactionCategory category,
                                           String responseId,
                                           String host,
                                           int port,
                                           String userName,
                                           String password) {
        super(id, rootId, parentId, direction, status, type, category, responseId, host, port);

        this.userName = userName;
        this.password = password;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
