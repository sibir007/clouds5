package io.github.sibir007.clouds5.client.gui.fx.controllers;

import io.github.sibir007.clouds5.core.Cloud;
import io.github.sibir007.clouds5.client.gui.fx.model.AccountBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

public class AddAccountWindowController {
    private final String EMPTY_TEXT_FOR_LABEL = "";
    @FXML
    private Label accountValidationLabel;
    private final String ACCOUNT_VALIDATION_LABEL_ERROR_MESSAGE = "INVALID ACCOUNT";
    private boolean validAccountCredentials = false;

    @FXML
    private Label userNameValidationLabel;
    private final String USER_NAME_VALIDATION_LABEL_ERROR_MESSAGE = "INVALID USER NAME";

    private boolean validUserName = false;
    @FXML
    private Label passwordValidationLabel;
    private final String PASSWORD_VALIDATION_LABEL_ERROR_MESSAGE = "INVALID PASSWORD";
    private boolean validPassword = false;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    private Model model;
    private int currentCloudIndex;
    private GuiClientCoordinator clientCoordinator;
    private MainViewController rootController;


// TODO: 28.07.2023 доделывать от initialise() и ниже
    public void initialize() {
        clientCoordinator = GuiClientCoordinator.getCoordinator();
        rootController = MainViewController.getRootController();
        model = Model.getModel();
        userName.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (validateUserName(newValue)) {
                validUserName = true;
                userNameValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
                if (validUserName && validPassword) {
                    validAccountCredentials = true;
                    accountValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
                }
                return;
            }
            validUserName = false;
            validAccountCredentials = false;
            accountValidationLabel.setText(ACCOUNT_VALIDATION_LABEL_ERROR_MESSAGE);
            userNameValidationLabel.setText(USER_NAME_VALIDATION_LABEL_ERROR_MESSAGE);
        }));
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (validatePassword(newValue)) {
                validPassword = true;
                passwordValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
                if (validPassword && validUserName) {
                    validAccountCredentials = true;
                    accountValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
                }
                return;
            }
            validPassword = false;
            validAccountCredentials = false;
            accountValidationLabel.setText(ACCOUNT_VALIDATION_LABEL_ERROR_MESSAGE);
            passwordValidationLabel.setText(PASSWORD_VALIDATION_LABEL_ERROR_MESSAGE);
        });
    }

    // TODO: 29.07.2023 придумать условия для проверки  password
    private boolean validatePassword(String password) {
        return true;
    }

    // TODO: 29.07.2023 придумать условия для userName
    private boolean validateUserName(String userNave) {
        return true;
    }

    public void setCurrentCloudIndex(int currentCloudIndex) {
        this.currentCloudIndex = currentCloudIndex;
    }

    @FXML
    private void createAccount(ActionEvent actionEvent) {
        if (validAccountCredentials) {
            createAccount(currentCloudIndex);
            hideWindow();
            return;
        }
        accountValidationLabel.setText(ACCOUNT_VALIDATION_LABEL_ERROR_MESSAGE);
    }


    public void createAccount(int currentCloudIndex) {
        assert Objects.nonNull(currentCloudIndex);
        Cloud cloud = model.getCloud(currentCloudIndex);
        cloud.addAccount(new AccountBeenImpl(userName.getText(), password.getText()));
    }

    @FXML
    private void cancelCreateAccount(ActionEvent actionEvent) {
        hideWindow();
    }

    private void hideWindow() {
        clearAllValue();
        userName.getScene().getWindow().hide();
    }

    private void clearAllValue() {
        userName.setText(EMPTY_TEXT_FOR_LABEL);
        password.setText(EMPTY_TEXT_FOR_LABEL);
        accountValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
        passwordValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
        userNameValidationLabel.setText(EMPTY_TEXT_FOR_LABEL);
    }
}
