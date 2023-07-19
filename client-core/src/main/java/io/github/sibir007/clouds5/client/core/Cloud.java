package io.github.sibir007.clouds5.client.core;


import java.util.List;

public interface Cloud {
    String getHost();
    void setHost(String host);
    int getPort();
    void setPort(int port);

    boolean addAccount(Account account);

    boolean removeAccount(Account account);

    boolean setAccounts(List<Account> accounts);

    List<Account> getAccounts();


}
