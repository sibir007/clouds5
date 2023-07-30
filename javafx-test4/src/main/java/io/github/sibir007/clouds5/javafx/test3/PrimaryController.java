package io.github.sibir007.clouds5.javafx.test3;

import java.io.IOException;
import java.net.InetAddress;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController {

    public TextField host;
    public TextField port;
    public Label statusLabel;
    private Stage newCLoudWindow = new Stage();


    public void initialize(){
        initNewCLoudWindow();

    }

    private void initNewCLoudWindow() {
        FXMLLoader loader = new FXMLLoader(PrimaryController.class.getResource("addCloudWindow.fxml"));
        newCLoudWindow.setTitle("newCLoud");
        Parent parent;
        try {
            parent = loader.load();
            Scene scene = new Scene(parent);
            newCLoudWindow.setScene(scene);
        } catch (Throwable e) {
            System.out.println("in Cathc!!!!!!!!!!!!!!!!!!!!!!!!!!");

            throw new RuntimeException(e);
        }




    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void show(ActionEvent actionEvent) {
        statusLabel.setText("");
        newCLoudWindow.showAndWait();
    }

    public void createCloud(ActionEvent actionEvent) {
        statusLabel.setText("cloud created: " + host +":" + port);
        newCLoudWindow.close();
    }

    public void cancelCreateCloud(ActionEvent actionEvent) {
        newCLoudWindow.close();
    }
}
