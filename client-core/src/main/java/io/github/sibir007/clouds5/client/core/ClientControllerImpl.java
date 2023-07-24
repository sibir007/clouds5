package io.github.sibir007.clouds5.client.core;

public class ClientControllerImpl implements ClientController {
    private static ClientController clientController = new ClientControllerImpl();
    public static ClientController getClientController(){
        return clientController;
    }
    private ClientControllerImpl(){}


    private ClientControllerTask clientControllerTask = new ClientControllerTask();


    @Override
    public void setCloudsClient(PostedCloudsClient cloudsClient) {

    }

    @Override
    public void addCloud(Cloud cloud) {

    }
    @Override
    public void newAccount(Cloud cloud, Account account){

    }

    @Override
    public void closeAccount(Cloud cloud, Account account){

    }

    @Override
    public void editAccount(Cloud cloud, Account oldAccount, Account newAccount) {

    }
    public void authoriseAccount(Cloud cloud, Account account){

    }
}
