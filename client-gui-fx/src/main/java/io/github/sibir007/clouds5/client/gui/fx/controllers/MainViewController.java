package io.github.sibir007.clouds5.client.gui.fx.controllers;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.ClientControllerTask;
import io.github.sibir007.clouds5.client.core.Cloud;
import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainViewController  implements PostedCloudsClient {

    @FXML
    private BorderPane mainView;

    @FXML
    private void selectMenageFilesView(ActionEvent actionEvent){

    }

    @FXML
    private void selectMenageCloudsView(ActionEvent actionEvent) {
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
