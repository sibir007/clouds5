package io.github.sibir007.clouds5.client.core;

import java.util.Set;

public interface Cloud {
    String getUrl();
    int getPort();
    Set<Account> getAccounts();

}
