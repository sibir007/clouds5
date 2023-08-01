package io.github.sibir007.clouds5.client.gui.fx.util;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.Cloud;
import io.github.sibir007.clouds5.client.gui.fx.model.AccountBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.CloudBeenImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.AccountImpl;
import io.github.sibir007.clouds5.client.gui.fx.model.CloudImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BeenModelConverter {
    private static Logger logger = LogManager.getLogger();

    private BeenModelConverter() {
    }

    public static ArrayList<CloudImpl> convertFromBeen(ObservableList<CloudBeenImpl> beenClouds) {
        logger.trace("im convertFromBeen");
        ArrayList<CloudImpl> clouds = new ArrayList<>();
        for (Cloud beenCloud : beenClouds) {
            List<Account> cloudAccounts = getSimpleCloudAccounts(beenCloud);
            String host = beenCloud.getHost();
            int port = beenCloud.getPort();
            CloudImpl cloud = new CloudImpl(host, port);
            cloud.setAccounts(cloudAccounts);
            clouds.add(cloud);
        }
        logger.trace(clouds);
        return clouds;
    }

    public static List<Account> getSimpleCloudAccounts(Cloud beenCloud) {
        List<Account> beenCloudAccounts = beenCloud.getAccounts();
        ArrayList<Account> accounts = new ArrayList<>();
        for (Account beenAccount : beenCloudAccounts) {
            Account account = getSimpleAccount(beenAccount);
            accounts.add(account);
        }
        return accounts;
    }

    public static Account getSimpleAccount(Account beenAccount) {
        String userName = beenAccount.getUserName();
        String password = beenAccount.getPassword();
        AccountImpl account = new AccountImpl(userName, password);
        return account;
    }


    public static ObservableList<CloudBeenImpl> convertToBeen(ArrayList<CloudImpl> clouds) {
        logger.trace("im convertToBeen");
        logger.trace(clouds);
        ObservableList<CloudBeenImpl> beenClouds = FXCollections.observableArrayList();
        for (CloudImpl cloud : clouds) {
            CloudBeenImpl beenCloud = getBeenCloudFromCloud(cloud);

            beenClouds.add(beenCloud);
        }
        logger.trace(beenClouds);
        return beenClouds;
    }

    public static CloudBeenImpl getBeenCloudFromCloud(Cloud cloud) {
        List<Account> beenCloudAccounts = getBeenCloudAccounts(cloud);
        String host = cloud.getHost();
        int port = cloud.getPort();
        CloudBeenImpl beenCloud = new CloudBeenImpl(host, port);
        beenCloud.setAccounts(beenCloudAccounts);
        return beenCloud;
    }

    private static List<Account> getBeenCloudAccounts(Cloud cloud) {
        logger.trace("in getBeenCloudAccounts");
        logger.trace(cloud);
        List<Account> cloudAccounts = cloud.getAccounts();
        List<Account> beenCloudAccounts = FXCollections.observableArrayList();
        for (Account account : cloudAccounts) {
            String userName = account.getUserName();
            String password = account.getPassword();
            AccountBeenImpl beenAccount = new AccountBeenImpl(userName, password);
            beenCloudAccounts.add(beenAccount);
        }
        logger.trace(beenCloudAccounts);
        return beenCloudAccounts;
    }
}
