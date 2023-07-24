package io.github.sibir007.clouds5.client.gui.fx.controllers;

import io.github.sibir007.clouds5.client.core.*;

public class GuiClientCoordinator {
    private static GuiClientCoordinator coordinator = new GuiClientCoordinator();

    public static GuiClientCoordinator getCoordinator(){
        return coordinator;
    }

    private ClientController clientController;

    private GuiClientCoordinator(){

    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }




    public void addCloud(Cloud cloud) {

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
