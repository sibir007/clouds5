package io.github.sibir007.clouds5.core.transactions;

public class AddCloudTransaction extends AbstractSimpleTransaction {

    public AddCloudTransaction(String id,
                               String rootId,
                               String parentId,
                               Direction direction,
                               Status status,
                               String responseId,
                               String host,
                               int port) {
        super(id,
                rootId,
                parentId,
                direction,
                status,
                TransactionType.ADD_CLOUD,
                TransactionCategory.CLIENT,
                responseId,
                host,
                port);
    }
}
