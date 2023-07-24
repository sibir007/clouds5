package io.github.sibir007.clouds5.client.core;

public interface ClientController {
    void setCloudsClient(PostedCloudsClient cloudsClient);
    void addCloud(Cloud cloud);
    void newAccount(Cloud cloud, Account account);
    void closeAccount(Cloud cloud, Account account);
    void editAccount(Cloud cloud, Account oldAccount, Account newAccount);
    void authoriseAccount(Cloud cloud, Account account);

    public static ClientController getClientControllerImpl(){
        return ClientControllerImpl.getClientController();
    }

    public static ClientController getClientControllerPlug(){
        return ClientControllerPlug.getClientController();
    }

}
