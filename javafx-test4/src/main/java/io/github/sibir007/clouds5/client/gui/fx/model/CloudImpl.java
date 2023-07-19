package io.github.sibir007.clouds5.client.gui.fx.model;

import io.github.sibir007.clouds5.client.core.Account;
import io.github.sibir007.clouds5.client.core.Cloud;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.*;

public class CloudImpl implements Cloud, Serializable {
    private String host;
    private int port;
    private List<Account> accounts = new ArrayList<>();
    public CloudImpl(String host, int port){
        this.host = host;
        this.port = port;
    }
    @Override
    public String getHost() {
        return host;
    }
    public void setHost(String host){
        this.host = host;
    }

    @Override
    public int getPort() {
        return port;
    }

    public void setPort(int port){
        this.port = port;
    }

    public boolean addAccount(Account account){
        return accounts.add(account);
    }

    public boolean removeAccount(Account account) {
        return accounts.remove(account);
    }

    @Override
    public boolean setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        return true;
    }

    public List<Account> getAccounts(){
        return accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CloudImpl cloud = (CloudImpl) o;
        return Objects.equals(host, cloud.getHost()) & port == cloud.getPort();
    }

    @Override
    public int hashCode() {
        return Objects.hash(host) + port;
    }

    @Override
    public String toString() {
        return "CloudImpl{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
