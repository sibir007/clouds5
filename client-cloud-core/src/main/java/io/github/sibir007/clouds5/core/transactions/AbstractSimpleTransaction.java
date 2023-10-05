package io.github.sibir007.clouds5.core.transactions;

import io.github.sibir007.clouds5.core.transactions.AutorisedTransaction;
import io.github.sibir007.clouds5.core.transactions.BaseTransaction;

public abstract class AbstractSimpleTransaction extends BaseTransaction implements SimpleTransaction {

private final String host;
private final int port;


    protected AbstractSimpleTransaction(String id,
                                        String rootId,
                                        String parentId,
                                        Direction direction,
                                        Status status,
                                        TransactionType type,
                                        TransactionCategory category,
                                        String responseId,
                                        String host,
                                        int port) {
        super(id, rootId, parentId, Complexity.SIMPLE, direction, status, type, category, responseId);

        this.host = host;
        this.port = port;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }
}
