package io.github.sibir007.clouds5.client.core;

public interface CloudsClient {


    void setClientController(ClientControllerTask clientController);
    void addCloud(CloudImpl cloud);
    void connectCLoud(CloudImpl cloud);
    void addAccount(AccountImpl account);
    void autorizeAccount(AccountImpl account);

}
