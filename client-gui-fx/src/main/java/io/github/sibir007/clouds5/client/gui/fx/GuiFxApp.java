package io.github.sibir007.clouds5.client.gui.fx;

import io.github.sibir007.clouds5.client.core.ClientController;
import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiFxApp extends Application {
    private ClientController clientController;
    private PostedCloudsClient guiCloudsClient;

    @Override
    public void init() throws Exception {
        clientController = new ClientController();
    }

    @Override
    public void start(Stage stage) throws Exception {

        guiCloudsClient = new guiCloudsClient();
        guiCloudsClient.

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

}

