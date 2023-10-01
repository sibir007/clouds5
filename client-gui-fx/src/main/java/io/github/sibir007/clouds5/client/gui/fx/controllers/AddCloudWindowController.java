package io.github.sibir007.clouds5.client.gui.fx.controllers;

import io.github.sibir007.clouds5.client.gui.fx.util.Util;
import io.github.sibir007.clouds5.client.gui.fx.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddCloudWindowController {
    private final String EMPTY_TEXT_FOR_LABEL = "";
    @FXML
    private Label cloudValidationLabel;
    private final String CLOUD_VALIDATION_LABEL_ERROR_MESSAGE = "INVALID CLOUD ADDRESS";
    private boolean validCLoudAddress = false;

    @FXML
    private Label hostValidationLabel;
    private final String HOST_VALIDATION_LABEL_ERROR_MESSAGE = "INVALID HOST IP";

    private boolean validHost = false;
    @FXML
    private Label portValidationLabel;
    private final String PORT_VALIDATION_LABEL_ERROR_MESSAGE = "INVALID HOST PORT";
    private boolean validPort = false;
    @FXML
    private TextField host;
    @FXML
    private TextField port;
    private Model model;
    //    private Window window;
    private GuiClientCoordinator clientCoordinator;
    private MainViewController rootController;


    public void initialize() {
        clientCoordinator = GuiClientCoordinator.getCoordinator();
        rootController = MainViewController.getRootController();
        model = Model.getModel();
        host.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (Util.validateIP(newValue)) {
                validHost = true;
                hostValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
                if (validHost && validPort) {
                    validCLoudAddress = true;
                    cloudValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
                }
                return;
            }
            validHost = false;
            validCLoudAddress = false;
            cloudValidationLabel.setText(CLOUD_VALIDATION_LABEL_ERROR_MESSAGE);
            hostValidationLabel.setText(HOST_VALIDATION_LABEL_ERROR_MESSAGE);
        }));
        port.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Util.validatePort(newValue)) {
                validPort = true;
                portValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
                if (validPort && validHost) {
                    validCLoudAddress = true;
                    cloudValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
                }
                return;
            }
            validPort = false;
            validCLoudAddress = false;
            cloudValidationLabel.setText(CLOUD_VALIDATION_LABEL_ERROR_MESSAGE);
            portValidationLabel.setText(PORT_VALIDATION_LABEL_ERROR_MESSAGE);
        });
    }

    @FXML
    private void addCloud(ActionEvent actionEvent) {
        if (validCLoudAddress) {
            addCloud(host.getText(), getPort());
            hideWindow();
            return;
        }
        cloudValidationLabel.setText(CLOUD_VALIDATION_LABEL_ERROR_MESSAGE);
        return;
    }


    public void addCloud(String host, int port) {
        clientCoordinator.addCloud(host, port);
    }


    private boolean validatePort() {
        return Util.validatePort(port.getText());
    }

    private boolean validateHost() {
        return Util.validateIP(host.getText());
    }

    @FXML
    private void cancelAddCloud(ActionEvent actionEvent) {
        hideWindow();
    }

    private void hideWindow() {
        clearAllValue();
        host.getScene().getWindow().hide();
    }

    private void clearAllValue() {
        host.setText(EMPTY_TEXT_FOR_LABEL);
        port.setText(EMPTY_TEXT_FOR_LABEL);
        cloudValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
        portValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
        hostValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
    }

    private int getPort() throws NumberFormatException {
        return Integer.valueOf(port.getText());
    }
}
