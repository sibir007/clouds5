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
    String getTransactionResponseId();

    public enum Direction implements TransactionsTypes{
        IN(1),
        OUT(2);
        private int dbId;

        private Direction(int dbId){
            this.dbId = dbId;
        }

        @Override
        public int getDbId() {
            return dbId;
        }
    }


    public enum Status implements TransactionsTypes{
        NEW(1),
        PROCESSING(2),
        OK(3),
        REJECTED(4),
        NO_CONNECTION(5),
        FILED(6),
        EXCEPTION(7);
        private int dbId;

        private Status(int dbId){
            this.dbId = dbId;
        }

        @Override
        public int getDbId() {
            return dbId;
        }
    }

    public enum TransactionType implements TransactionsTypes{
        ADD_CLOUD(1),
        ADD_ACCOUNT(2),
        DEFAULT(3);
        private int dbId;
        private TransactionType(int dbId){
            this.dbId = dbId;
        }

        @Override
        public int getDbId() {
            return dbId;
        }
    }

    public enum TransactionCategory  implements TransactionsTypes{

        CLIENT(1),
        AGENT(2);

        private int dbId;
        private TransactionCategory(int dbId){
            this.dbId = dbId;
        }

        @Override
        public int getDbId() {
            return dbId;
        }
    }

    public enum Complexity implements TransactionsTypes {
        SIMPLE(1),
        COMPOSITE(2);
        private int dbId;
        private Complexity(int dbId){
            this.dbId = dbId;
        }

        @Override
        public int getDbId() {
            return dbId;
        }
    }

    public interface TransactionsTypes {
        int getDbId();
    }
}
