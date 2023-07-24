package io.github.sibir007.clouds5.client.gui.fx.controllers;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.ClientController;
import io.github.sibir007.clouds5.client.core.Cloud;
import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import io.github.sibir007.clouds5.client.gui.fx.model.Model;
import javafx.application.Platform;

public class PostedCloudClientImpl  implements PostedCloudsClient {
    private static PostedCloudsClient client = new PostedCloudClientImpl();
    public static PostedCloudsClient getInstants(){
        return client;
    }
    private Model model = Model.getModel();
    private PostedCloudClientImpl(){}

    public void setClientController(ClientController clientController) {

    }
    /*
    TODO необходимо сделать реализаци конвертации Клоуда в БиинКлоуд и также для Аккаунта
    после этого можно запускать редаклирование модели из потока Platform
     */
    @Override
    public void addCloud(Cloud cloud) {
        Platform.runLater(() -> {

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
