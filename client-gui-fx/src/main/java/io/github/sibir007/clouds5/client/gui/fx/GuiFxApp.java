package io.github.sibir007.clouds5.client.gui.fx;

import io.github.sibir007.clouds5.client.core.controllers.ClientController;
import io.github.sibir007.clouds5.client.core.controllers.ClientControllerPlug;
import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import io.github.sibir007.clouds5.client.gui.fx.controllers.GuiClientCoordinator;
import io.github.sibir007.clouds5.client.gui.fx.controllers.MainViewController;
import io.github.sibir007.clouds5.client.gui.fx.controllers.PostedCloudClientImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.AccountBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.CloudBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.Model;
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
import java.util.Objects;

public class GuiFxApp extends Application {
    private static Logger logger = LogManager.getLogger();


    private PostedCloudsClient guiCloudsClient;
    private Scene scene;

    @Override
    public void init() throws Exception {
        logger.trace("init method");
    }

    @Override
    public void start(Stage stage) throws IOException {
        logger.trace("start method");
        URL url = GuiFxApp.class.getResource("mainView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        BorderPane mainView = fxmlLoader.load();
        MainViewController mainViewController = fxmlLoader.getController();

        scene = new Scene(mainView, 640, 480);

        stage.setScene(scene);

//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("io.github.sibir007.clouds5.client.gui.fx/mainView.fxml"));
//        BorderPane mainView = fxmlLoader.load();
//        Scene scene = new Scene(mainView);
//        stage.setScene(scene);
        stage.setTitle("Clouds client");
        System.out.println(getClass().getClassLoader().getResource("log4j2.xml"));
        GuiClientCoordinator.getCoordinator().setClientController(ClientController.getClientControllerPlug());
        PostedCloudsClient cloudsClient = PostedCloudClientImpl.getClient();
        ClientController clientController1 = ClientController.getClientControllerPlug();
        clientController1.setCloudsClient(cloudsClient);
        initModel();
        stage.show();




    }

    private void initModel() {
        CloudBeenImpl cloudBeen1, cloudBeen2;
        AccountBeenImpl accountBeen1, accountBeen2, accountBeen3, accountBeen4, accountBeen5;
        cloudBeen1 = new CloudBeenImpl("djflsjflsf", 67);
        cloudBeen2 = new CloudBeenImpl("ttttttttttt", 99);
        accountBeen1 = new AccountBeenImpl("dima", "sldjflksdjfdslkjf");
        accountBeen2 = new AccountBeenImpl("pasha", "///////////////");
        accountBeen3 = new AccountBeenImpl("kolua", "jjjjjjjjjjjjjjjj");
        accountBeen4 = new AccountBeenImpl("micha", "171717171717171");
        accountBeen5 = new AccountBeenImpl("cveta", "@@@@@@@@@@@@@@@");
        cloudBeen1.addAccount(accountBeen1);
        cloudBeen1.addAccount(accountBeen3);
        cloudBeen2.addAccount(accountBeen2);
        cloudBeen2.addAccount(accountBeen4);
        cloudBeen2.addAccount(accountBeen5);
        Model.getModel().getClouds().addAll(cloudBeen1, cloudBeen2);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        URL url = GuiFxApp.class.getResource(fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        return fxmlLoader.load();
    }

    @Override
    public void stop() throws Exception {
        logger.trace("stop method");
        Model.getModel().save();
//        Thread.currentThread().wait(100000000l);
        ((ClientControllerPlug)ClientController.getClientControllerPlug()).stopController();
        super.stop();
    }

    public static void main(String[] args) {
        logger.trace("main method");

//        logger.fatal("fatal logger");
//        logger.error("error logger");
//        logger.warn("warn logger");
//        logger.info("info logger");
//        logger.debug("debug logger");
//        logger.trace("trace logger");

        launch(args);

    }


}

