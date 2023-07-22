package io.github.sibir007.clouds5.client.gui.fx.controllers;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.gui.fx.model.AccountBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.CloudBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.Model;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ManageCloudsViewController {
    private static Logger logger = LogManager.getLogger();
    @FXML
    private BorderPane accounts, clouds;
    @FXML
    private Button newAccountButton, deleteAccountButton, authoriseAccountButton, editAccountButton;
    @FXML
    private Button addCloudButton, deleteCloudButton, editCloudButton;

    @FXML
    private TableView cloudsAccountsTable, cloudsTable;
    private Model model;

    public void initialize() {
        logger.trace("in initialise");
        model = Model.getModel();
        initCloudsTable();
        initCloudsAccountsTable();

        if (!cloudsTable.getItems().isEmpty()) {

        }

    }

    private void initCloudsAccountsTable() {
        TableColumn<Account, String> userNameCol = new TableColumn<>("UserName");
//        CloudBeenImpl cloudBeen = (CloudBeenImpl)model.getClouds().get(0);
//        logger.trace("get accounts - ", cloudBeen.getAccounts());
//        AccountBeenImpl accountBeen = (AccountBeenImpl)cloudBeen.getAccounts().get(0);
        userNameCol.setCellValueFactory(new PropertyValueFactory<>(AccountBeenImpl.getNameUserNameProperty()));
//        userNameCol.setCellValueFactory(new PropertyValueFactory<>(accountBeen.getUserNameProperty().getName()));
        TableColumn<Account, Integer> passCol = new TableColumn<>("password");
        passCol.setCellValueFactory(new PropertyValueFactory<>(AccountBeenImpl.getNamePasswordProperty()));
//        passCol.setCellValueFactory(new PropertyValueFactory<>(((AccountBeenImpl)((CloudBeenImpl)model.getClouds().get(0)).getAccounts().get(0)).getPasswordProperty().getName()));

        cloudsAccountsTable.getColumns().setAll(userNameCol, passCol);
//        cloudsTable.getFocusModel().focusedCellProperty().addListener((observable, oldValue, newValue) -> {
//            logger.trace(((TablePosition)oldValue).toString());
//        });
        cloudsAccountsTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            logger.trace(newValue);
        });
        int currentSelectionIndex = cloudsTable.getSelectionModel().getFocusedIndex();
        logger.trace(currentSelectionIndex);
        if (!(currentSelectionIndex == -1)) {
            setCloudAccountsTable(currentSelectionIndex);
        }
//        setCloudAccountsTable(currentSelectionIndex);
        cloudsTable.getFocusModel().focusedCellProperty().addListener((observable, oldValue, newValue) -> {
            int currentIndex = ((TablePosition) newValue).getRow();
            setCloudAccountsTable(currentIndex);
            logger.trace(((TablePosition)oldValue).toString());
        });
    }

    private void initCloudsTable() {
        cloudsTable.setItems(model.getClouds());
        TableColumn<CloudBeenImpl, String> hostCol = new TableColumn<>("host");
        hostCol.setCellValueFactory(new PropertyValueFactory<>(CloudBeenImpl.getNameHostProperty()));
//        hostCol.setCellValueFactory(new PropertyValueFactory<>(((CloudBeenImpl)model.getClouds().get(0)).getHostProperty().getName()));
        TableColumn<CloudBeenImpl, Integer> portCol = new TableColumn<>("port");
        portCol.setCellValueFactory(new PropertyValueFactory<>(CloudBeenImpl.getNamePortProperty()));
//        portCol.setCellValueFactory(new PropertyValueFactory<>(((CloudBeenImpl)model.getClouds().get(0)).getPortProperty().getName()));
        cloudsTable.getColumns().setAll(hostCol, portCol);

    }

    private void setCloudAccountsTable(int currentSelectionIndex) {
        CloudBeenImpl currentCloudBeen = (CloudBeenImpl) model.getClouds().get(currentSelectionIndex);
        ObservableList<Account> currentCloudBeenAccounts = currentCloudBeen.getAccounts();
        cloudsAccountsTable.getItems().setAll(currentCloudBeenAccounts);
    }

    public void addCloud(ActionEvent actionEvent) {
        logger.trace("addCloudPressed");
        logger.error("err in addCloud");
    }


    public void deleteCloud(ActionEvent actionEvent) {
        logger.error("err in deleteCloud");
        logger.trace("deleteCloudPressed");

    }

    public void editCloud(ActionEvent actionEvent) {
        logger.error("err in editCloud");
        logger.trace("editCloudPressed");

    }

    public void changeLabelText(String str) {

    }

    public void editAccount(ActionEvent actionEvent) {
    }

    public void authoriseAccount(ActionEvent actionEvent) {

    }

    public void deleteAccount(ActionEvent actionEvent) {

    }

    public void createNewAccount(ActionEvent actionEvent) {

    }
}
