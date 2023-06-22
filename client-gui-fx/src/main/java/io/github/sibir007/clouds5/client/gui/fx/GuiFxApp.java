package io.github.sibir007.clouds5.client.gui.fx;

import io.github.sibir007.clouds5.client.core.ClientControllerTask;
import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiFxApp extends Application {
    private ClientControllerTask clientController;
    private PostedCloudsClient guiCloudsClient;

    @Override
    public void init() throws Exception {
        clientController = new ClientControllerTask();
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

