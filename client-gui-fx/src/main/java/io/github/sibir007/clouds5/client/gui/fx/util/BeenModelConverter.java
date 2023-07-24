package io.github.sibir007.clouds5.client.gui.fx.util;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.Cloud;
import io.github.sibir007.clouds5.client.gui.fx.model.AccountBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.CloudBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.persistance.AccountImpl;
import io.github.sibir007.clouds5.client.gui.fx.persistance.CloudImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BeenModelConverter {
    private static Logger logger = LogManager.getLogger();
    private BeenModelConverter(){}
    public static ArrayList<CloudImpl> convertFromBeen(ObservableList<Cloud> beenClouds){
        logger.trace("im convertFromBeen");
        ArrayList<CloudImpl> clouds = new ArrayList<>();
        for (Cloud beenCloud: beenClouds) {
            ArrayList<AccountImpl> cloudAccounts = getSimpleCloudAccounts(beenCloud);
            String host = beenCloud.getHost();
            int port = beenCloud.getPort();
            CloudImpl cloud = new CloudImpl(host, port);
            cloud.setAccounts(cloudAccounts);
            clouds.add(cloud);
        }
        logger.trace(clouds);
        return clouds;
    }

    public static ArrayList<AccountImpl> getSimpleCloudAccounts(Cloud beenCloud) {
        List<Account> beenCloudAccounts = beenCloud.getAccounts();
        ArrayList<AccountImpl> accounts = new ArrayList<>();
        for (Account beenAccount: beenCloudAccounts) {
            AccountImpl account = getSimpleAccount(beenAccount);
            accounts.add(account);
        }
        return accounts;
    }

    public static AccountImpl getSimpleAccount(Account beenAccount) {
        String userName = beenAccount.getUserName();
        String password = beenAccount.getPassword();
        AccountImpl account = new AccountImpl(userName, password);
        return account;
    }

//TODO сделать реализацию сонвертации отдельно по Клоудам и Аккаунтем
    public static ObservableList<Cloud> convertToBeen(ArrayList<CloudImpl> clouds){
        logger.trace("im convertToBeen");
        logger.trace(clouds);
        ObservableList<Cloud> beenClouds = FXCollections.observableArrayList();
        for (CloudImpl cloud: clouds) {
            ObservableList<Account> beenCloudAccounts = getBeenCloudAccounts(cloud);
            String host = cloud.getHost();
            int port = cloud.getPort();
            CloudBeenImpl beenCloud = new CloudBeenImpl(host, port);
            beenCloud.setAccounts(beenCloudAccounts);
            beenClouds.add(beenCloud);
        }
        logger.trace(beenClouds);
        return beenClouds;
    }

    private static ObservableList<Account> getBeenCloudAccounts(CloudImpl cloud) {
        logger.trace("in getBeenCloudAccounts");
        logger.trace(cloud);
        List<AccountImpl> cloudAccounts = cloud.getAccounts();
        ObservableList<Account> beenCloudAccounts = FXCollections.observableArrayList();
        for (AccountImpl account: cloudAccounts) {
            String userName = account.getUserName();
            String password = account.getPassword();
            Account beenAccount = new AccountBeenImpl(userName, password);
            beenCloudAccounts.add(beenAccount);
        }
        logger.trace(beenCloudAccounts);
        return beenCloudAccounts;
    }
}
