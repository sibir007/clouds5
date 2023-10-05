package io.github.sibir007.clouds5.core.transactions;

public abstract class BaseTransaction implements Transaction {


    private final String id;
    private final String rootId;
    private final String parentId;
    private final Complexity complexity;
    private final Direction direction;
    private final Status status;
    private final TransactionType type;
    private final TransactionCategory category;

    private final String transactionResponseId;

    protected BaseTransaction(String id,
                              String rootId,
                              String parentId,
                              Complexity complexity,
                              Direction direction,
                              Status status,
                              TransactionType type,
                              TransactionCategory category,
                              String transactionResponseId) {
        this.id = id;
        this.rootId = rootId;
        this.parentId = parentId;
        this.complexity = complexity;
        this.direction = direction;
        this.status = status;
        this.type = type;
        this.category = category;
        this.transactionResponseId = transactionResponseId;
    }



    public String getId() {
        return id;
    }

    @Override
    public String getRootId() {
        return rootId;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public Complexity getComplexity() {
        return complexity;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public TransactionType getType() {
        return type;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public String getTransactionResponseId(){
        return transactionResponseId;
    }
}
