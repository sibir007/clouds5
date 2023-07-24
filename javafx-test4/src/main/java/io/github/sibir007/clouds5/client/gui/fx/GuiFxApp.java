package io.github.sibir007.clouds5.client.gui.fx;

import io.github.sibir007.clouds5.client.core.ClientControllerTask;
import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class GuiFxApp extends Application {
    private ClientControllerTask clientController;
    private PostedCloudsClient guiCloudsClient;
    private static Scene scene;
    @Override
    public void init() throws Exception {
//        clientController = new ClientControllerTask();
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("mainView.fxml"), 640, 480);
        stage.setScene(scene);
//        guiCloudsClient = new guiCloudsClient();
        stage.show();


    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        URL url = GuiFxApp.class.getResource("mainView.fxml");
        if (url == null){
            System.out.println("URL is null");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

