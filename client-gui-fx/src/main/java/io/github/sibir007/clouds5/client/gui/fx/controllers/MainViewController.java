package io.github.sibir007.clouds5.client.gui.fx.controllers;

import io.github.sibir007.clouds5.client.core.*;
import io.github.sibir007.clouds5.client.gui.fx.GuiFxApp;
import io.github.sibir007.clouds5.client.gui.fx.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class MainViewController{
    private static Logger logger = LogManager.getLogger();
    private GuiClientCoordinator clientCoordinator;

    @FXML
    private BorderPane mainView;

    private Parent manageCloudsView;
    private ManageCloudsViewController manageCloudsViewController;
    private Parent manageFilesView;
    private ManageFilesViewController manageFilesViewController;
    private Model model;


    public void initialize() throws IOException {
        clientCoordinator = GuiClientCoordinator.getCoordinator();
        logger.trace("in initialize");
        model = Model.getModel();
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
}
