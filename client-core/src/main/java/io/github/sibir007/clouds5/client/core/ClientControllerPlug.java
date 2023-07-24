package io.github.sibir007.clouds5.client.core;

import io.github.sibir007.clouds5.client.core.commands.CloudTransactionTask;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

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
public class ClientControllerPlug implements ClientController {

    private static ClientController clientController = new ClientControllerPlug();

    public static ClientController getClientController() {
        return clientController;
    }

    private PostedCloudsClient cloudsClient;
    private ClientControllerTask clientControllerTask = new ClientControllerTask();



    private ClientControllerPlug() {

    }

    @Override
    public void setCloudsClient(PostedCloudsClient cloudsClient) {
        this.cloudsClient = cloudsClient;
    }

    @Override
    public void addCloud(Cloud cloud) {

    }
    @Override
    public void newAccount(Cloud cloud, Account account){

    }

    @Override
    public void closeAccount(Cloud cloud, Account account){

    }

    @Override
    public void editAccount(Cloud cloud, Account oldAccount, Account newAccount) {

    }
    public void authoriseAccount(Cloud cloud, Account account){

    }
}
