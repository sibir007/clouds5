package io.github.sibir007.clouds5.client.core;

import io.github.sibir007.clouds5.core.Account;
import io.github.sibir007.clouds5.core.Cloud;

public interface CloudsClient {

    void addCloud(Cloud cloud);
    void connectCLoud(Cloud cloud);
    void addAccount(Cloud cloud, Account account);
    void authorizeAccount(Cloud cloud, Account account);
    void beganTransaction();
    void endTransaction();

}
