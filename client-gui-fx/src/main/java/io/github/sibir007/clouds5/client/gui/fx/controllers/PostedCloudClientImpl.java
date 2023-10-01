package io.github.sibir007.clouds5.client.gui.fx.controllers;

import io.github.sibir007.clouds5.core.Account;
import io.github.sibir007.clouds5.core.Cloud;
import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import io.github.sibir007.clouds5.client.gui.fx.model.CloudBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.Model;
import io.github.sibir007.clouds5.client.gui.fx.util.BeenModelConverter;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: 01.08.2023  выполнена реализация модификации (добавления Cloud)
//  Model из отдельного потока ClientControllerPlug через PlatformRunLate(runnable)
//  доделывать остальные виды модификации
public class PostedCloudClientImpl implements PostedCloudsClient {
    private static Logger logger = LogManager.getLogger();
    private static PostedCloudsClient client = new PostedCloudClientImpl();

    public static PostedCloudsClient getClient() {
        return client;
    }

    private Model model = Model.getModel();
    private MainViewController rootController = MainViewController.getRootController();

    private PostedCloudClientImpl() {}


    @Override
    public void addCloud(Cloud cloud) {
        logger.trace("PostedCloudClientImpl addCloud() method");
        CloudBeenImpl cloudBeen = BeenModelConverter.getBeenCloudFromCloud(cloud);
        Platform.runLater(() -> {
            logger.trace("PostedCloudClientImpl ddCloud Platform.runLater runnable object");
            model.addCloud(cloudBeen);
        });

    }

    @Override
    public void connectCLoud(Cloud cloud) {
    }

    @Override
    public void addAccount(Cloud cloud, Account account) {
    }

    @Override
    public void authorizeAccount(Cloud cloud, Account account) {
    }

    @Override
    public void beganTransaction() {
    }

    @Override
    public void endTransaction() {
    }

    @Override
    public void postMessage(String msg) {
    }
}
