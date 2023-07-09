package io.github.sibir007.clouds5.client.gui.fx;

import io.github.sibir007.clouds5.client.core.ClientControllerTask;
import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import io.github.sibir007.clouds5.cloud.core.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;

public class GuiFxApp extends Application {
    private static Logger logger = LogManager.getLogger();


    private ClientControllerTask clientController;
    private PostedCloudsClient guiCloudsClient;
    private Scene scene;

    @Override
    public void init() throws Exception {
//        clientController = new ClientControllerTask();
    }

    @Override
    public void start(Stage stage) throws IOException {


        scene = new Scene(loadFXML("primary.fxml"), 640, 480);

        stage.setScene(scene);

//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("io.github.sibir007.clouds5.client.gui.fx/mainView.fxml"));
//        BorderPane mainView = fxmlLoader.load();
//        Scene scene = new Scene(mainView);
//        stage.setScene(scene);
        stage.show();


    }

    private static Parent loadFXML(String fxml) throws IOException {
        URL url = App.class.getResource(fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        return fxmlLoader.load();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);

//        logger.debug("debug logger");
//        logger.error("error logger");
//        logger.fatal("fatal logger");
//        logger.warn("warn logger");
//        logger.info("info logger");
//        logger.trace("trace logger");
    }


}

