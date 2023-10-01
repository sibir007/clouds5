package io.github.sibir007.clouds5.core.transactions;

public interface Transaction {
    static String reportMessage(Transaction transaction) {
        // TODO: 23.09.2023 доделать transaction report
        return "transaction string";
    }

    String getId();
    String getRootId();
    String getParentId();
    Complexity getComplexity();
    Direction getDirection();
    Status getStatus();
    TransactionType getType();
    TransactionCategory getCategory();


    public enum Direction{
        IN,
        OUT
    }


    public enum Status {
        NEW,
        PROCESSING,
        OK,
        REJECTED,
        NO_CONNECTION,
        FILED,
        EXCEPTION
    }

    public enum TransactionType {
        ADD_CLOUD, ADD_ACCOUNT,
    }

    public enum TransactionCategory {
        CLIENT,
        AGENT
    }

    public enum Complexity {
        SIMPLE,
        COMPOSITE
    }
}
