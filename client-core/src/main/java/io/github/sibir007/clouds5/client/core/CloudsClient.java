package io.github.sibir007.clouds5.client.core;

public interface CloudsClient {

    void addCloud(Cloud cloud);
    void connectCLoud(Cloud cloud);
    void addAccount(Cloud cloud, Account account);
    void authorizeAccount(Cloud cloud, Account account);
    void beganTransaction();
    void endTransaction();

}
