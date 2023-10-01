package io.github.sibir007.clouds5.client.core.controllers;

import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import io.github.sibir007.clouds5.client.core.commands.AddCloudTask;
import io.github.sibir007.clouds5.client.core.commands.CloudTransactionTask;
import io.github.sibir007.clouds5.client.core.commands.StopControllerTask;
import io.github.sibir007.clouds5.core.Account;
import io.github.sibir007.clouds5.core.Cloud;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Клас заглушка, принимает команды от Интерфейсов через GuiClientCoordinator
 * и кидает их назад в PostedCloudClient,
 * который в свою очередь модифицирует Model а также взаимодействует
 * с MainViewController для взаимодействия с пользователем через интерфейс
 * Сделан для отладки GUI интерфейса
 */

    /*
    TODO доделывать ClientControllerPlug, должен принимать
      команды от GuiClientCoordinator, формировать CloudTransactionTask и отдавать их в свою очередь должен
      выдавать обратно комантды в PostedCloudsClient, там в потоке Platform должна
      модифицироваться модель.
     */
public class ClientControllerPlug extends Thread implements ClientController {
    private static Logger logger = LogManager.getLogger();
    private static ClientControllerPlug clientController;

    public static ClientController getClientController() {
        if (clientController == null) {
            clientController = new ClientControllerPlug();
            logger.trace("ClientController created");
            clientController.start();
            logger.trace("ClientController started");
        }
        return clientController;
    }

    private PostedCloudsClient cloudsClient;
    private BlockingDeque<CloudTransactionTask> cloudTransactionTaskBlockingDeque = new LinkedBlockingDeque<>(1);


    private ClientControllerPlug() {
        super("ClientControllerPlug");
    }

    @Override
    public void setCloudsClient(PostedCloudsClient cloudsClient) {
        this.cloudsClient = cloudsClient;
    }

    @Override
    public void addCloud(Cloud cloud) {
        logger.trace("ClientController addCLoud method");

        try {
            cloudTransactionTaskBlockingDeque.putLast(AddCloudTask.COMMAND(cloud));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void newAccount(Cloud cloud, Account account) {

    }

    @Override
    public void closeAccount(Cloud cloud, Account account) {

    }

    @Override
    public void editAccount(Cloud cloud, Account oldAccount, Account newAccount) {

    }

    public void authoriseAccount(Cloud cloud, Account account) {

    }

    @Override
    public void run() {
        logger.trace("ClientController run() method started");
        CloudTransactionTask command;
        boolean CONTINUE = true;
        do {
            try {
                command = cloudTransactionTaskBlockingDeque.takeFirst();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (command.commandType()) {
                case ADD_CLOUD:
                    logger.trace("ClientController run() method ADD_CLOUD case");
                    cloudsClient.addCloud(((AddCloudTask) command).getCloud());
                    break;
                case STOP_CONTROLLER:
                    logger.trace("ClientController run() method STOP_CONTROLLER case");
                    CONTINUE = false;
                    break;
            }

        } while (CONTINUE);
    }

    public void stopController() {
        try {
            cloudTransactionTaskBlockingDeque.putLast(StopControllerTask.COMMAND());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
