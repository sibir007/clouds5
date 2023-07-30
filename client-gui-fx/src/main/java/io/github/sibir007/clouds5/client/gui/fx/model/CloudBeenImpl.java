package io.github.sibir007.clouds5.client.gui.fx.model;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.Cloud;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class CloudBeenImpl implements Cloud{
    private static final String nameHostProperty = "host";
    public static String getNameHostProperty(){
        return nameHostProperty;
    }
    private static final String namePortProperty = "port";
    public static String getNamePortProperty(){
        return namePortProperty;
    }
    private SimpleStringProperty host = new SimpleStringProperty(this, nameHostProperty);
    private SimpleIntegerProperty port = new SimpleIntegerProperty(this, namePortProperty);
    private ObservableList<Account> accounts = FXCollections.observableArrayList();
    public CloudBeenImpl(String host, int port){
        this.host.set(host);
        this.port.set(port);
    }

    public SimpleStringProperty getHostProperty(){
        return host;
    }

    public SimpleIntegerProperty getPortProperty(){
        return port;
    }
    @Override
    public String getHost() {
        return host.get();
    }
    public void setHost(String host){
        this.host.set(host);
    }

    @Override
    public int getPort() {
        return port.get();
    }

    public void setPort(int port){
        this.port.set(port);
    }

    public boolean addAccount(Account account){
        assert account instanceof AccountBeenImpl;
        return accounts.add(account);
    }

    public boolean removeAccount(Account account) {
        return accounts.remove(account);
    }

    public List<Account> getAccounts(){
        return accounts;
    }

    public boolean setAccounts(List<Account> accounts) {
        assert accounts instanceof ObservableList;
        return this.accounts.setAll(accounts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloudBeenImpl cloud = (CloudBeenImpl) o;
        return Objects.equals(host.get(), cloud.getHost()) & port.get() == cloud.getPort();
    }

    @Override
    public int hashCode() {
        return Objects.hash(host.get()) + port.get();
    }

    @Override
    public String toString() {
        return "CloudBeenImpl{" +
                "host='" + host.get() + '\'' +
                ", port='" + port.get() + '\'' +
                '}';
    }
}
