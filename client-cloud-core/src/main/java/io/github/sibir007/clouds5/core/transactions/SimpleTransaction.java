package io.github.sibir007.clouds5.core.transactions;

public interface SimpleTransaction extends Transaction{
    String getHost();
    int getPort();
    String getUserName();
    String getPassword();
    String getTransactionResponseId();
}
