package io.github.sibir007.clouds5.client.gui.fx.model;

import io.github.sibir007.clouds5.client.core.Cloud;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.*;

public class CloudImpl implements Cloud, Serializable {
    private SimpleStringProperty host = new SimpleStringProperty(this, "host");
    private SimpleIntegerProperty port = new SimpleIntegerProperty(this, "port");
    private ObservableList<AccountImpl> accounts = FXCollections.observableArrayList();
    public CloudImpl(String host, int port){
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

    @Override
    public int getPort() {
        return port.get();
    }

    public boolean addAccount(AccountImpl account){
        return accounts.add(account);
    }

    public boolean removeAccount(AccountImpl account) {
        return accounts.remove(account);
    }

    public ObservableList<AccountImpl> getAccounts(){
        return accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloudImpl cloud = (CloudImpl) o;
        return Objects.equals(host.get(), cloud.getHost()) & port.get() == cloud.getPort();
    }

    @Override
    public int hashCode() {
        return Objects.hash(host.get()) + port.get();
    }

    @Override
    public String toString() {
        return "CloudImpl{" +
                "host='" + host.get() + '\'' +
                ", port='" + port.get() + '\'' +
                '}';
    }
}
