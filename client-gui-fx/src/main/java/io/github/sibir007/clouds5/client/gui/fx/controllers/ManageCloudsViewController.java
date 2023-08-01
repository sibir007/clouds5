package io.github.sibir007.clouds5.client.gui.fx.controllers;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.Cloud;
import io.github.sibir007.clouds5.client.gui.fx.GuiFxApp;
import io.github.sibir007.clouds5.client.gui.fx.model.AccountBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.CloudBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

//TODO делать взаимодействие интерфейса через GuiClientCoordinator в ClientControllerPlug
public class ManageCloudsViewController {
    private static Logger logger = LogManager.getLogger();
    private GuiClientCoordinator clientCoordinator;
    private MainViewController rootController;

    @FXML
    private BorderPane accounts, clouds;
    @FXML
    private Button newAccountButton, deleteAccountButton, authoriseAccountButton, editAccountButton;
    @FXML
    private Button addCloudButton, deleteCloudButton, editCloudButton;

    @FXML
    private TableView cloudsAccountsTable, cloudsTable;
    private Model model;

    //Sub interfaces
    private Stage addCloudWindow;
    private AddCloudWindowController addCloudWindowController;
    private Stage addAccountWindow;
    private AddAccountWindowController addAccountWindowController;
    private Stage editCloudWindow;
    private EditCloudWindowController editCloudWindowController;


    //placeholder for empty cloudsAccountTable
    private ObservableList<Account> placeholderEmptyAccountTable = FXCollections.emptyObservableList();

    private int currentCLoudIndex;
    private int currentAccountIndex;


    public void initialize() {
        rootController = MainViewController.getRootController();
        clientCoordinator = GuiClientCoordinator.getCoordinator();
        logger.trace("in initialise");
        model = Model.getModel();
        initCloudsTable();
        initCloudsAccountsTable();
        initAddCloudWindow();
        initAddAccountWindow();
        initEditCloudWindow();
    }

    private void initEditCloudWindow() {
        editCloudWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(GuiFxApp.class.getResource("editCloudWindow.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        editCloudWindowController = loader.getController();
        Scene scene = new Scene(root);

        editCloudWindow.setScene(scene);
        editCloudWindow.initModality(Modality.APPLICATION_MODAL);
    }

    private void initAddAccountWindow() {
        addAccountWindow = new Stage();

        FXMLLoader loader = new FXMLLoader(GuiFxApp.class.getResource("addAccountWindow.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addAccountWindowController = loader.getController();
        Scene scene = new Scene(root);

        addAccountWindow.setScene(scene);
        addAccountWindow.initModality(Modality.APPLICATION_MODAL);
    }

    private void initAddCloudWindow() {
        addCloudWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(GuiFxApp.class.getResource("addCloudWindow.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addCloudWindowController = loader.getController();
        Scene scene = new Scene(root);

        addCloudWindow.setScene(scene);
        addCloudWindow.initModality(Modality.APPLICATION_MODAL);
//        addCloudWindow.setAlwaysOnTop(true);
    }

    private void initCloudsAccountsTable() {
        TableColumn<Account, String> userNameCol = new TableColumn<>("UserName");
        userNameCol.setCellValueFactory(new PropertyValueFactory<>(AccountBeenImpl.getNameUserNameProperty()));
        TableColumn<Account, Integer> passCol = new TableColumn<>("password");
        passCol.setCellValueFactory(new PropertyValueFactory<>(AccountBeenImpl.getNamePasswordProperty()));
        cloudsAccountsTable.getColumns().setAll(userNameCol, passCol);
        cloudsAccountsTable.getFocusModel().focusedIndexProperty().addListener((observable, oldValue, newValue) -> currentAccountIndex = (int) newValue);
        cloudsAccountsTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            logger.trace("selectedIndexProperty().addListener((observable, oldValue, newValue) newValue:" + newValue);
        });
        cloudsAccountsTable.getItems().addListener((ListChangeListener) observable -> {
//            setCloudAccountsTable(currentCLoudIndex);
        });

//        int currentSelectionIndex = cloudsTable.getSelectionModel().getFocusedIndex();
//        logger.trace("currentSelectionIndex: " + currentSelectionIndex);
        setCloudAccountsTable(currentCLoudIndex);
//
//        if (!(currentSelectionIndex == -1)) {
//            setCloudAccountsTable(currentSelectionIndex);
//        }
//        setCloudAccountsTable(currentSelectionIndex);
        cloudsTable.getItems().addListener((ListChangeListener) c -> {
            logger.trace("cloudsTable changed: " + c.toString());
            int focusedIndex = cloudsTable.getFocusModel().getFocusedIndex();
            logger.trace("current focused index: " + focusedIndex);
            setCloudAccountsTable(focusedIndex);
//
//            if (focusedIndex != -1) {
//                setCloudAccountsTable(focusedIndex);
//            }
        });
        cloudsTable.getFocusModel().focusedCellProperty().addListener((observable, oldValue, newValue) -> {
            logger.trace("focused value change: " + newValue.toString());
            int currentIndex = ((TablePosition) newValue).getRow();
            setCloudAccountsTable(currentIndex);
//
//            if (currentIndex != -1) {
//                setCloudAccountsTable(currentIndex);
//            }
            logger.trace(((TablePosition) newValue).toString());
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
        cloudsTable.getFocusModel().focusedIndexProperty().addListener((observable, oldValue, newValue) -> currentCLoudIndex = (int) newValue);
        currentCLoudIndex = cloudsTable.getFocusModel().getFocusedIndex();

    }

    private void setCloudAccountsTable(int currentSelectionIndex) {
        logger.trace("setCLoudAccountsTable(" + currentSelectionIndex + ")");
        if (currentSelectionIndex == -1) {
            logger.trace("if (currentSelectionIndex == -1)");
            logger.trace("placeholderEmptyAccountTable" + placeholderEmptyAccountTable);
            cloudsAccountsTable.setItems(placeholderEmptyAccountTable);
//            cloudsAccountsTable.getItems().setAll(placeholderEmptyAccountTable);
            return;
        }
        CloudBeenImpl currentCloudBeen = (CloudBeenImpl) model.getClouds().get(currentSelectionIndex);
        ObservableList<Account> currentCloudBeenAccounts = (ObservableList<Account>) currentCloudBeen.getAccounts();
        cloudsAccountsTable.setItems(currentCloudBeenAccounts);
//        cloudsAccountsTable.getItems().setAll(currentCloudBeenAccounts);
    }

    @FXML
    private void addCloud(ActionEvent actionEvent) {
        logger.trace("addCloudPressed");
        addCloud();
    }

    public void addCloud() {
        addCloudWindow.show();
    }

    @FXML
    private void deleteCloud(ActionEvent actionEvent) {
        logger.trace("deleteCloudPressed");
        //validation absence Clouds
        if (currentCLoudIndex != -1) {
            deleteCloud(currentCLoudIndex);
        }
    }

    public Cloud deleteCloud(int cloudIndex) {
        assert Objects.nonNull(cloudIndex) && cloudIndex >= 0;
        return clientCoordinator.deleteCLoud(cloudIndex);
    }

    @FXML
    private void editCloud(ActionEvent actionEvent) {
        logger.trace("editCloudPressed");
        editCloud(currentCLoudIndex);
    }

    public void editCloud(int oldCloudIndex) {
        assert Objects.nonNull(oldCloudIndex) && oldCloudIndex >= 0;
        editCloudWindowController.setOldCloud(oldCloudIndex);
        editCloudWindow.show();
    }

    @FXML
    private void editAccount(ActionEvent actionEvent) {
    }

    @FXML
    private void authoriseAccount(ActionEvent actionEvent) {

    }

    @FXML
    private void deleteAccount(ActionEvent actionEvent) {

    }

    @FXML
    private void createNewAccount(ActionEvent actionEvent) {
        createNewAccount(currentCLoudIndex);
    }

    public void createNewAccount(int cloudIndex) {
        assert Objects.nonNull(cloudIndex);
        addAccountWindowController.setCurrentCloudIndex(cloudIndex);
        addAccountWindow.showAndWait();
    }
}
