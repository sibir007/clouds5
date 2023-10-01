package io.github.sibir007.clouds5.core.transactions;

import java.util.List;
import java.util.UUID;

public abstract class BaseTransaction implements Transaction {


    private final String id;
    private final String rootId;
    private final String parentId;
    private final Complexity complexity;
    private final Direction direction;
    private final Status status;
    private final TransactionType type;
    private final TransactionCategory category;


    protected BaseTransaction(String id,
                              String rootId,
                              String parentId,
                              Complexity complexity,
                              Direction direction,
                              Status status,
                              TransactionType type,
                              TransactionCategory category) {
        this.id = id;
        this.rootId = rootId;
        this.parentId = parentId;
        this.complexity = complexity;
        this.direction = direction;
        this.status = status;
        this.type = type;
        this.category = category;
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


}
