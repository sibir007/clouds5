package io.github.sibir007.clouds5.client.gui.fx.controllers;

//import io.github.sibir007.clouds5.client.gui.fx.model.CloudBeenImpl;
import io.github.sibir007.clouds5.client.core.controllers.ClientController;
import io.github.sibir007.clouds5.client.gui.fx.model.CloudBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.Model;
import io.github.sibir007.clouds5.core.Account;
import io.github.sibir007.clouds5.core.Cloud;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class GuiClientCoordinator {
    private static Logger logger = LogManager.getLogger();
    private static GuiClientCoordinator coordinator = new GuiClientCoordinator();

    public static GuiClientCoordinator getCoordinator(){
        return coordinator;
    }

    private ClientController clientController;

    private Model model = Model.getModel();

    private GuiClientCoordinator(){

    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    public void addCloud(CloudBeenImpl cloud) {
        logger.trace("GuiClientCoordinator addCloud() method");
        clientController.addCloud(cloud);
    }

    public void addCloud(String host, int port){
        addCloud(new CloudBeenImpl(host, port));
    }

    public CloudBeenImpl deleteCLoud(int cloudIndex){
        assert Objects.nonNull(cloudIndex) && cloudIndex >= 0 ;
        return  model.removeCloud(cloudIndex);
    }

    // TODO: 30.07.2023  заглушка, переделывать замену Cloud на CloudsClient
    public void editCLoud(CloudBeenImpl oldCloud, CloudBeenImpl newCloud){
        model.addCloud((newCloud));
        model.removeCloud(oldCloud);
    }

    public void editCLoud(int oldCLoudIndex, String newCloudHost, int newCloudPort) {
        List<Account> accounts = model.getCloud(oldCLoudIndex).getAccounts();
        CloudBeenImpl newCloudBeen = new CloudBeenImpl(newCloudHost, newCloudPort);
        newCloudBeen.setAccounts(accounts);
        editCLoud(model.getCloud(oldCLoudIndex), newCloudBeen);
    }
    
    public void editCloud(int oldCLoudIndex, String newCloudHost, int newCloudPort){
        
    }



    public void newAccount(Cloud cloud, Account account) {

    }


    public void closeAccount(Cloud cloud, Account account) {

    }


    public void editAccount(Cloud cloud, Account oldAccount, Account newAccount) {

    }


    public void authoriseAccount(Cloud cloud, Account account) {

    }

    
}
