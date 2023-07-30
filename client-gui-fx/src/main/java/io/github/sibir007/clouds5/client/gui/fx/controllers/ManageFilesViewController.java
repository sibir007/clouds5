package io.github.sibir007.clouds5.client.gui.fx.controllers;

public class ManageFilesViewController {
    private GuiClientCoordinator clientCoordinator;
    private MainViewController rootController;

    public void initialize(){
        rootController = MainViewController.getRootController();
        clientCoordinator = GuiClientCoordinator.getCoordinator();
    }

}
