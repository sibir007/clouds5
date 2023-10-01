package io.github.sibir007.clouds5.client.core.controllers;

import io.github.sibir007.clouds5.client.core.commands.AddCloudTask;
import io.github.sibir007.clouds5.client.core.commands.CloudTransactionTask;
import io.github.sibir007.clouds5.client.core.transactions.managers.AddCloudTransactionManager;
import io.github.sibir007.clouds5.core.Account;
import io.github.sibir007.clouds5.core.Cloud;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Client controller spec")
public class ClientControllerImplSpec {


    @Nested
    @DisplayName("ClientControllerImpl Thread testing")
    class ClientControllerImplThreadTesting {
        @Test
        @DisplayName("call singleton should return same instance and be started")
        void call_singleton_should_return_same_instance_and_be_started() throws InterruptedException {
            ClientControllerImpl clientController1 = ClientController.getClientControllerImplSingleton();
            ClientControllerImpl clientController2 = ClientController.getClientControllerImplSingleton();
//            Thread.sleep(1000);
            assertSame(clientController2, clientController1, () -> "should be the same instance");
            assertTrue(clientController2.isAlive(), () -> "thread should be started");
            assertTrue(clientController2.getName().contains("singletonInstance"), () -> "thread name should contain singletonInstance");
            clientController2.doStop();
            clientController2.join();
            assertFalse(clientController2.isAlive(), () -> "thread should be died");
        }

        @Test
        @DisplayName("call newInstance should return different instance and be started")
        void call_newInstance_should_return_different_instance_and_be_started() throws InterruptedException {
            ClientControllerImpl clientController1 = ClientControllerImpl.getClientControllerNewInstanceStarted();
            ClientControllerImpl clientController2 = ClientControllerImpl.getClientControllerNewInstanceStarted();
            assertNotSame(clientController2, clientController1, () -> "should be different instance");
            assertTrue(clientController1.isAlive(), () -> "thread should be started");
            assertTrue(clientController2.isAlive(), () -> "thread should be started");
            clientController2.doStop();
            clientController1.doStop();
            clientController2.join();
            clientController1.join();
        }
        @Test
        @DisplayName("call doStop should stop clientController thread")
        void callStopShouldStoppingTread() throws InterruptedException {
            ClientControllerImpl clientController = ClientControllerImpl.getClientControllerNewInstanceStarted();
            assertTrue(clientController.isAlive(), "should be Alive");
            clientController.doStop();
            clientController.join();
            Thread.State state = clientController.getState();
            assertEquals(Thread.State.TERMINATED, state, "state should be Terminated");
        }


    }

    @Nested
    @DisplayName("addCloud() feature testing")
    class AddCloudFeatureTesting {
        @Test
        @DisplayName("addCloud() call should result creating AddCloudTask object," +
                "send it in cloudTransactionTaskBlockingDeque, " +
                "decode task in clientController thread, " +
                "call AddCloudTransactionManager with Cloud parameter")
        void addCloudResult() throws InterruptedException {
            ClientControllerImpl clientController = new ClientControllerImpl();
            AddCloudTransactionManager addCloudTransactionManager = mock(AddCloudTransactionManager.class);
            BlockingDeque<CloudTransactionTask> cloudTransactionTaskBlockingDeque = spy(new LinkedBlockingDeque<>(10));
            ArgumentCaptor<Cloud> argumentCaptor1 = ArgumentCaptor.forClass(Cloud.class);
            ArgumentCaptor<AddCloudTask> argumentCaptor2 = ArgumentCaptor.forClass(AddCloudTask.class);
            clientController.setAddCloudTransactionManager(addCloudTransactionManager);
            clientController.setCloudTransactionTaskBlockingDeque(cloudTransactionTaskBlockingDeque);
            clientController.start();
            Cloud cloud = new Cloud() {
                @Override
                public String getHost() {
                    return null;
                }

                @Override
                public void setHost(String host) {

                }

                @Override
                public int getPort() {
                    return 0;
                }

                @Override
                public void setPort(int port) {

                }

                @Override
                public boolean addAccount(Account account) {
                    return false;
                }

                @Override
                public boolean removeAccount(Account account) {
                    return false;
                }

                @Override
                public boolean setAccounts(List<Account> accounts) {
                    return false;
                }

                @Override
                public List<Account> getAccounts() {
                    return null;
                }

                @Override
                public void setCloudAvailabilityStatus(CloudAvailabilityStatus status) {

                }

                @Override
                public CloudAvailabilityStatus getCloudAvailabilityStatus() {
                    return null;
                }
            };
            clientController.addCloud(cloud);
            clientController.doStop();
            clientController.join();
            verify(cloudTransactionTaskBlockingDeque).putLast(argumentCaptor2.capture());
            assertEquals(AddCloudTask.class, argumentCaptor2.getValue().getClass());
            verify(cloudTransactionTaskBlockingDeque, atLeastOnce()).takeFirst();
            verify(addCloudTransactionManager).processTransaction(cloud);
        }


    }


}
