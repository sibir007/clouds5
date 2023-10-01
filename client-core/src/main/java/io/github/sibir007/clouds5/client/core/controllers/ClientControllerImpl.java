package io.github.sibir007.clouds5.client.core.controllers;

import io.github.sibir007.clouds5.client.core.PostedCloudsClient;
import io.github.sibir007.clouds5.client.core.commands.AddCloudTask;
import io.github.sibir007.clouds5.client.core.commands.CloudTransactionTask;
import io.github.sibir007.clouds5.client.core.transactions.managers.AddCloudTransactionManager;
import io.github.sibir007.clouds5.core.Account;
import io.github.sibir007.clouds5.core.Cloud;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientControllerImpl extends Thread implements ClientController {
    private static final int BLOCKING_DEQUE_CAPACITY = 100;
    private static final Logger logger = LogManager.getLogger();
    private static final String THREAD_NAME = "clientControllerImpl";


    private static ClientControllerImpl clientControllerSingleton;

    private static AtomicInteger countInstance = new AtomicInteger();
    //    private AddCloudTransactionManager addCloudTransactionManager = new AddCloudTransactionManager();
    private AddCloudTransactionManager addCloudTransactionManager;
    private PostedCloudsClient cloudsClient;

    //only for testing purpose
    void setAddCloudTransactionManager(AddCloudTransactionManager manager) {
        addCloudTransactionManager = manager;
    }

    private volatile BlockingDeque<CloudTransactionTask> cloudTransactionTaskBlockingDeque = new LinkedBlockingDeque<>(BLOCKING_DEQUE_CAPACITY);

    //only for testing purpose
    void setCloudTransactionTaskBlockingDeque(BlockingDeque<CloudTransactionTask> deque) {
        cloudTransactionTaskBlockingDeque = deque;
    }


    public static synchronized ClientControllerImpl getClientControllerSingletonStarted() {
        if (clientControllerSingleton == null) {
            clientControllerSingleton = new ClientControllerImpl("instance " + countInstance.incrementAndGet() + " singletonInstance");
            clientControllerSingleton.start();
            logger.trace(clientControllerSingleton.getName() + " started");
        }
        return clientControllerSingleton;
    }

    //only for testing
    protected static ClientControllerImpl getClientControllerNewInstanceStarted() {
        ClientControllerImpl clientController = new ClientControllerImpl("instance " + countInstance.incrementAndGet());
        clientController.start();
        logger.trace(clientController.getName() + " started");
        return clientController;
    }

    protected ClientControllerImpl() {
        this("instance " + countInstance.incrementAndGet());
//        getClass().getClassLoader().getResource("log4j2.xml").getFile();
        logger.trace("ClientControllerImpl() constructor");
        System.out.println("logger url " + getClass().getClassLoader().getResource("log4j2.xml").getFile());
//        this("instance " + countInstance.incrementAndGet());
//        this.addCloudTransactionManager = addCloudTransactionManager;
    }

    protected ClientControllerImpl(String instanceName) {
        super(THREAD_NAME + " " + instanceName);


        logger.trace("ClientControllerImpl(String instanceName) constructor, argument: " + instanceName);
//        logger.trace(logger.toString());
//        logger.g
        initTransactionManagers();
    }


    private void initTransactionManagers() {
        initAddCloudTransactionManager();

    }

    private void initAddCloudTransactionManager() {
        AddCloudTransactionManager addCloudTransactionManager = AddCloudTransactionManager.getAddCloudTransactionManagerSingleton();
        setAddCloudTransactionManager(addCloudTransactionManager);
    }

    private void setCloudClientForTransactionManagers() {
        addCloudTransactionManager.setCloudsClient(cloudsClient);
    }

//    private void initTransactionManagers() {
//        setAddCloudTransactionManager(new AddCloudTransactionManager());
//    }


    @Override
    public void setCloudsClient(PostedCloudsClient cloudsClient) {
        this.cloudsClient = cloudsClient;
        setCloudClientForTransactionManagers();
    }

    @Override
    public void addCloud(Cloud cloud) {
        logger.trace("ClientController addCLoud method");

        try {
            cloudTransactionTaskBlockingDeque.putLast(AddCloudTask.COMMAND(cloud));
            logger.trace("cloudTransactionTaskBlockingDeque.putLast(AddCloudTask.COMMAND(cloud))");
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
        CloudTransactionTask command;

        while (!isInterrupted()) {
            logger.trace("Thread " + getName() + " isInterrupted state " + isInterrupted());
            try {

                command = cloudTransactionTaskBlockingDeque.takeFirst();
                logger.trace("cloudTransactionTaskBlockingDeque.takeFirst()");
                switch (command.commandType()) {
                    case ADD_CLOUD:
                        logger.trace("ClientController run() method ADD_CLOUD case");
                        logger.trace("command " + command);
                        logger.trace("cloud " + ((AddCloudTask) command).getCloud());
                        addCloudTransactionManager.processTransaction(((AddCloudTask) command).getCloud());
                        break;
//                    case STOP_CONTROLLER:
//                        logger.trace("ClientController run() method STOP_CONTROLLER case");
//                        CONTINUE = false;
//                        break;
                }
            } catch (InterruptedException e) {
                logger.trace(Thread.currentThread() + " interrupted");
//                INTERRUPTED = true;
//                throw new RuntimeException(e);
                interrupt();
            }
        }
//        countStartedInstance.decrementAndGet();
        logger.trace(Thread.currentThread() + " exit out of run()");
    }

    public void doStop() {
        interrupt();
    }

//    void setAddCloudTransactionManager(AddCloudTransactionManager addCloudTransactionManager) {
//        this.addCloudTransactionManager = addCloudTransactionManager;
//    }
}
