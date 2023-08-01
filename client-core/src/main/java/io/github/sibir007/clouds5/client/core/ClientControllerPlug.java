package io.github.sibir007.clouds5.client.core;

import io.github.sibir007.clouds5.client.core.commands.AddCloud;
import io.github.sibir007.clouds5.client.core.commands.CloudTransactionTask;
import io.github.sibir007.clouds5.client.core.commands.CommandType;
import io.github.sibir007.clouds5.client.core.commands.StopController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static io.github.sibir007.clouds5.client.core.commands.CommandType.ADD_CLOUD;
import static io.github.sibir007.clouds5.client.core.commands.CommandType.STOP_CONTROLLER;

/**
 * Клас заглушка, принимает команды от Интерфейса и кидает их назад
 * через отдельный поток ClientControllerTask в PostedCloudClient.
 * Сделан для отладки GUI интерфейса
 */

    /*
    TODO доделывать ClientControllerPlug, должен принимать
      команды от GuiClientCoordinator, формировать CloudTransactionTask и отдавать их в
      очередь потока ClientControllerTask он в свою очередь должен
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
        super("ClientControllerTask");
    }

    @Override
    public void setCloudsClient(PostedCloudsClient cloudsClient) {
        this.cloudsClient = cloudsClient;
    }

    @Override
    public void addCloud(Cloud cloud) {
        logger.trace("ClientController addCLoud method");

        try {
            cloudTransactionTaskBlockingDeque.putLast(AddCloud.COMMAND(cloud));
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
                    cloudsClient.addCloud(((AddCloud) command).getCloud());
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
            cloudTransactionTaskBlockingDeque.putLast(StopController.COMMAND());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
