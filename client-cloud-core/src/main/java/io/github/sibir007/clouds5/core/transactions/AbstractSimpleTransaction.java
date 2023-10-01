package io.github.sibir007.clouds5.core.transactions;

public abstract class AbstractSimpleTransaction extends BaseTransaction implements SimpleTransaction {
    protected final static String NOUSED_NAME = "UNKNOWN";
    protected final static String NOUSED_PASSWORD = "UNKNOWN";
    private final String host;
    private final int port;
    private final String userName;
    private final String password;
    private final String responseId;


    protected AbstractSimpleTransaction(String id,
                                        String rootId,
                                        String parentId,
                                        Direction direction,
                                        Status status,
                                        TransactionType type,
                                        TransactionCategory category,
                                        String host,
                                        int port,
                                        String userName,
                                        String password,
                                        String responseId) {
        super(id, rootId, parentId, Complexity.SIMPLE, direction, status, type, category);
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.responseId = responseId;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getTransactionResponseId() {
        return responseId;
    }
}
