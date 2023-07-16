package io.github.sibir007.clouds5.client.gui.fx.model;

import io.github.sibir007.clouds5.client.core.Cloud;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CloudImpl implements Cloud, Serializable {
    private String host;
    private int port;
    private Set<AccountImpl> accounts = new HashSet<>();
    public CloudImpl(String host, int port){
        this.host = host;
        this.port = port;
    }
    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    public boolean addAccount(AccountImpl account){
        return accounts.add(account);
    }

    public boolean removeAccount(AccountImpl account) {
        return accounts.remove(account);
    }

    public Set<AccountImpl> getAccounts(){
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
