package io.github.sibir007.clouds5.client.gui.fx.controllers;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.ClientControllerTask;
import io.github.sibir007.clouds5.client.core.Cloud;
import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import io.github.sibir007.clouds5.client.gui.fx.GuiFxApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MainViewController  implements PostedCloudsClient {
    private static Logger logger = LogManager.getLogger();

    @FXML
    private BorderPane mainView;

    private Parent manageCloudsView;
    private ManageCloudsViewController manageCloudsViewController;
    private Parent manageFilesView;
    private ManageFilesViewController manageFilesViewController;


    public void initialize() throws IOException {
        logger.trace("in initialize");
        initializeManageCloudsView();
        initializeManageFilesView();
        mainView.setCenter(manageCloudsView);

    }


    private void initializeManageCloudsView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiFxApp.class.getResource("manageCloudsView.fxml"));
        manageCloudsView = fxmlLoader.load();
        manageCloudsViewController = fxmlLoader.getController();
    }


    private void initializeManageFilesView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiFxApp.class.getResource("manageFilesView.fxml"));
        manageFilesView = fxmlLoader.load();
        manageFilesViewController = fxmlLoader.getController();
    }

    public void selectMenageFilesView(ActionEvent actionEvent){
        logger.trace("in selectMenageFilesView pressed");
        mainView.setCenter(manageFilesView);
    }


    public void selectMenageCloudsView(ActionEvent actionEvent) {
        logger.trace("in selectMenageCloudsView pressed");
        mainView.setCenter(manageCloudsView);
    }

    @Override
    public void setClientController(ClientControllerTask clientController) {

    }

    @Override
    public void addCloud(Cloud cloud) {

    }

    @Override
    public void connectCLoud(Cloud cloud) {

    }

    @Override
    public void addAccount(Cloud cloud, Account account) {

    }

    @Override
    public void authorizeAccount(Cloud cloud, Account account) {

    }

    @Override
    public void postMessage(String msg) {

    }

    public void switchToSecondary(ActionEvent actionEvent) {
    }
}
