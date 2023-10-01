package io.github.sibir007.clouds5.client.core.controllers;

import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import io.github.sibir007.clouds5.core.Account;
import io.github.sibir007.clouds5.core.Cloud;

public interface ClientController {
    void setCloudsClient(PostedCloudsClient cloudsClient);
    void addCloud(Cloud cloud);
    void newAccount(Cloud cloud, Account account);
    void closeAccount(Cloud cloud, Account account);
    void editAccount(Cloud cloud, Account oldAccount, Account newAccount);
    void authoriseAccount(Cloud cloud, Account account);

    public static ClientControllerImpl getClientControllerImplSingleton(){
        return ClientControllerImpl.getClientControllerSingletonStarted();
    }
//    public static ClientControllerImpl getClientControllerImplNewInstance(){
//        return ClientControllerImpl.getClientControllerNewInstance();
//    }

    public static ClientController getClientControllerPlug(){
        return ClientControllerPlug.getClientController();
    }

}
