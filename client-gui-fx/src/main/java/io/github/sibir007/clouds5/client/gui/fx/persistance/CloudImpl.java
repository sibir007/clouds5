package io.github.sibir007.clouds5.client.gui.fx.persistance;

import io.github.sibir007.clouds5.client.core.Account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CloudImpl implements Serializable {
    private String host;
    private int port;
    private ArrayList<AccountImpl> accounts = new ArrayList<>();
    public CloudImpl(String host, int port){
        this.host = host;
        this.port = port;
    }



    public String getHost() {
        return host;
    }
    public void setHost(String host){
        this.host = host;
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port){
        this.port = port;
    }

    public boolean addAccount(AccountImpl account){
        return accounts.add(account);
    }

    public boolean removeAccount(AccountImpl account) {
        return accounts.remove(account);
    }

    public ArrayList<AccountImpl> getAccounts(){
        return accounts;
    }

    public boolean setAccounts(ArrayList<AccountImpl> accounts) {
        return this.accounts.addAll(accounts);
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
        return "CloudBeenImpl{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                "accounts=" + accounts.toString() +
                '}';
    }


}
