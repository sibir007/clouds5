package io.github.sibir007.clouds5.client.core;

public interface CloudsClient {


    void setClientController(ClientControllerTask clientController);
    void addCloud(Cloud cloud);
    void connectCLoud(Cloud cloud);
    void addAccount(Cloud cloud, Account account);
    void authorizeAccount(Cloud cloud, Account account);

}
