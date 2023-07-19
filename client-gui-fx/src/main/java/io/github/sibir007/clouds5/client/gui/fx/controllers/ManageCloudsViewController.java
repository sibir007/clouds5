package io.github.sibir007.clouds5.client.gui.fx.controllers;

import io.github.sibir007.clouds5.client.gui.fx.model.CloudBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ManageCloudsViewController {
    private static Logger logger = LogManager.getLogger();
    @FXML
    private BorderPane accounts;
    @FXML
    private Label accountsLabel;
    @FXML
    private Button addCloudButton;
    @FXML
    private Button deleteCloudButton;
    @FXML
    private Button editCloudButton;
    @FXML
    private TableView cloudsTable;
    private Model model;

    public void initialize(){
        logger.trace("in initialise");
        model = Model.getModel();
        initCloudsTable();
    }

    private void initCloudsTable() {
        cloudsTable.setItems(model.getClouds());
        TableColumn<CloudBeenImpl, String> hostCol = new TableColumn<>("host");
        hostCol.setCellValueFactory(new PropertyValueFactory<>(model.getClouds().get(0).getHostProperty().getName()));
        TableColumn<CloudBeenImpl, Integer> portCol = new TableColumn<>("port");
        portCol.setCellValueFactory(new PropertyValueFactory<>(model.getClouds().get(0).getPortProperty().getName()));
        cloudsTable.getColumns().setAll(hostCol, portCol);
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

    public void changeLabelText(String str){
        accountsLabel.setText(str);
    }
}
